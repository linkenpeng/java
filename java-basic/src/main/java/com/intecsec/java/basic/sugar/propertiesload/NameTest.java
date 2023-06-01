package com.intecsec.java.basic.sugar.propertiesload;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 测试ResourceBundle加载方式
 * @author Tom
 * 以JDK9及以上运行，程序正常，以JDK8运行，出现乱码，无法解析UTF-8文件
 */
public class NameTest {

	public static void main(String[] args) {
		Locale myLocale = Locale.getDefault();
		
		System.out.println(myLocale); //zh_CN 

		// 根据指定语言_国家环境加载资源文件
		ResourceBundle bundle = ResourceBundle.getBundle("msg", myLocale);

		// 从资源文件中取得的消息
		System.out.println(bundle.getString("name"));  //陈良育
		
		bundle = ResourceBundle.getBundle("msg2", myLocale);

		// 从资源文件中取得的消息
		System.out.println(bundle.getString("name"));  //大中国

	}

}
