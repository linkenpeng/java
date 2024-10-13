package com.intecsec.flink;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.ververica.cdc.connectors.mysql.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.DebeziumSourceFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class FlinkCDC {
    public static void main(String[] args) throws Exception {

        //启动webui，绑定本地web-ui端口号
        Configuration configuration=new Configuration();
        configuration.setInteger(RestOptions.PORT, 8081);

        //获取配置文件
        Properties properties = new Properties();
        String currentDir = System.getProperty("user.dir");
        String configFilePath = currentDir + File.separator + "application.properties";
        File file = new File(configFilePath);
        if (!file.exists()){
            ClassLoader classLoader = FlinkCDC.class.getClassLoader();
            URL resourceUrl = classLoader.getResource("application.properties");
            configFilePath = resourceUrl.getPath();
        }

        System.out.println("配置文件：" + configFilePath);
        try (FileInputStream configFile = new FileInputStream(configFilePath)) {
            properties.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Object syncTime = properties.get("syncTime");
        DateTime dateTime = DateUtil.parse((String) syncTime, "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime);

        //获取Flink 执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(configuration);
        env.setParallelism(1);//设置并行度为1


        //通过FlinkCDC构建SourceFunction
        DebeziumSourceFunction<String> sourceFunction = MySqlSource.<String>builder()
                .hostname("localhost")
                .port(3306)
                .username("root")
                .password("123456")
                .databaseList("test")
                .tableList("test.blog")
                .deserializer(new CommonStringDebeziumDeserializationSchema("localhost",3306)) //设置序列化器
                .startupOptions(StartupOptions.timestamp(dateTime.getTime())) //可以选择从头开始、最新的、指定时间戳位置
                .build();
        DataStreamSource<String> dataStreamSource = env.addSource(sourceFunction);

        dataStreamSource.addSink(new RichSinkFunction<String>() {
            @Override
            public void invoke(String value, Context context) throws Exception {

                //打印出重新组装的JSON数据
                System.out.println("json->: "+value);
                super.invoke(value, context);
            }
        });

        //启动任务
        env.execute("FlinkCDC");

    }
}