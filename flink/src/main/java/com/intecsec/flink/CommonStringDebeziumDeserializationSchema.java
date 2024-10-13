package com.intecsec.flink;

import com.alibaba.fastjson.JSONObject;
import com.ververica.cdc.connectors.shaded.org.apache.kafka.connect.data.Field;
import com.ververica.cdc.connectors.shaded.org.apache.kafka.connect.data.Struct;
import com.ververica.cdc.connectors.shaded.org.apache.kafka.connect.source.SourceRecord;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;

public class CommonStringDebeziumDeserializationSchema implements DebeziumDeserializationSchema<String> {

    private String host;
    private int port;

    public CommonStringDebeziumDeserializationSchema(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void deserialize(SourceRecord record, Collector<String> out){

        //打印原始的数据
        System.out.println(record);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("host",host);
        jsonObject.put("port",port);
        jsonObject.put("file", record.sourceOffset().get("file"));
        jsonObject.put("pos", record.sourceOffset().get("pos"));
        jsonObject.put("ts_sec", record.sourceOffset().get("ts_sec"));

        String[] name = record.valueSchema().name().split("\\.");
        jsonObject.put("db", name[1]);
        jsonObject.put("table", name[2]);

        Struct value = ((Struct) record.value());
        String operatorType = value.getString("op");
        jsonObject.put("operator_type", operatorType);

        // insert update
        if (!"d".equals(operatorType)) {
            Struct after = value.getStruct("after");
            jsonObject.put("after", parseRecord(after));
        }
        // update & delete
        if ("u".equals(operatorType) || "d".equals(operatorType)) {
            Struct source = value.getStruct("before");
            jsonObject.put("before", parseRecord(source));
        }
        jsonObject.put("parse_time", System.currentTimeMillis() / 1000);

        out.collect(jsonObject.toString());
    }

    private JSONObject parseRecord(Struct after) {
        JSONObject jsonObject = new JSONObject();
        for (Field field : after.schema().fields()) {
            Object o = after.get(field.name());
            jsonObject.put(field.name(),o);
        }

        return jsonObject;
    }

    public TypeInformation<String> getProducedType() {
        return BasicTypeInfo.STRING_TYPE_INFO;
    }
}
