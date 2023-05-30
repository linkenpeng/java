package com.intecsec.java.basic.complex.webservice;


import com.intecsec.java.basic.complex.webservice.webxml.MobileCodeWS;
import com.intecsec.java.basic.complex.webservice.webxml.MobileCodeWSSoap;

public class WebServiceClient {

	/**
	 * use wsimport  to parse wsdl
	 * wsimport -keep -verbose http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL
	 * copy the generated class to this project, and invoke them
	 * 
	 * 调用手机号查询web service
	 * @param args
	 */
	public static void main(String[] args) {
		MobileCodeWS mobileCodeWS = new MobileCodeWS();
		MobileCodeWSSoap mobileCodeWSSoap = mobileCodeWS.getMobileCodeWSSoap();
		String tel=mobileCodeWSSoap.getMobileCodeInfo("13722222222",null);  //修改为有效号码
        System.out.println(tel);  
	}	
}
