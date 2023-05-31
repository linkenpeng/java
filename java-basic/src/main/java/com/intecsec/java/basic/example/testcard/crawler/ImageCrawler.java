package com.intecsec.java.basic.example.testcard.crawler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class ImageCrawler implements Runnable{
    private static String tempPath = "c:/temp/image";// 图片保存目录
    private static String BLANK = " ";
    // phantomjs参数配置
    private static String binPath = "e:/phantomjs-2.1.1-windows/bin/phantomjs.exe";// 插件引入地址
    private static String jsPath = "e:/phantomjs-2.1.1-windows/examples/screen.js";// js引入地址

    String url;
    
    public ImageCrawler(String url)
    {
    	this.url = url;
    }    
    
    public void run()
    {
    	try 
    	{
			captureScreen();
		} 
    	catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void captureScreen() throws Exception{
        String imgagePath = tempPath+"/"+System.currentTimeMillis() + (new Random().nextInt(1000)) +".png";//图片路径
        //Java中使用Runtime和Process类运行外部程序
        Process process = Runtime.getRuntime().exec(cmd(imgagePath,url));
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String tmp = "";
        while ((tmp = reader.readLine()) != null) {
        }
        int exitVal = process.waitFor();
        if(exitVal == 0)
        {
        	//System.out.println("success");
        }
        reader.close();
        if (process != null) {
            process.destroy();
            process = null;
        }
    }
    
 // 构造cmd命令
    public static String cmd(String imgagePath, String url) {
    	String str = binPath + BLANK + jsPath + BLANK + url + BLANK + imgagePath;
    	System.out.println(str);
        return binPath + BLANK + jsPath + BLANK + url + BLANK + imgagePath;
    }
    //关闭命令
    public static void close(Process process, BufferedReader bufferedReader) throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (process != null) {
            process.destroy();
            process = null;
        }
    }
    
    public static void main(String[] args) throws Exception {
        String url = "https://www.baidu.com/";//以百度网站首页截屏
        new ImageCrawler(url).captureScreen();
    }
}