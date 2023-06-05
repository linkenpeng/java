package com.intecsec.java.basic.proxy.aop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlReader {

	
	public static void readXml(String filePath){
		String xml = load(filePath);
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			Document document;
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			StringReader sr = new StringReader(xml);		  
			InputSource is = new InputSource(sr);
			document = dbBuilder.parse(is);
			Element root = document.getDocumentElement();
			findMethod(root.getChildNodes());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void findMethod(NodeList elementList){
		for (int i = 0; i < elementList.getLength(); i++) {
			Node elementNode = elementList.item(i);
			//System.out.println("allnode: "+elementNode.getNodeName());
			if (elementNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) elementNode;
				String name = element.getNodeName();
				if(name.equalsIgnoreCase("aop")){
					readMethod(element.getChildNodes());
				}
				else{
					findMethod(element.getChildNodes());
				}
			}
		}
	}
	
	private static void readMethod(NodeList elementList){
		String methodName = "";
		for (int i = 0; i < elementList.getLength(); i++) {
			Node elementNode = elementList.item(i);
			if (elementNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) elementNode;
				String name = element.getNodeName();
				if(name.equals("method")){
					if(methodName==null||methodName.length()==0)methodName = element.getFirstChild().getTextContent();
				}
				else if(name.equals("type")){
					String type = element.getFirstChild().getTextContent();
					if(type.equals("after")){
						ProxyHandler.afterMethod = methodName;
					}
					else{
						ProxyHandler.beforeMethod = methodName;
					}
					
				}
			}
		}
	}
	
	private static String load(String path){
		try{
			File file = new File(path);
	        FileReader reader = new FileReader(file);
	        BufferedReader bReader = new BufferedReader(reader);
	        StringBuilder sb = new StringBuilder();
	        String s = "";
	        while ((s =bReader.readLine()) != null) {
	            sb.append(s + "\n");
	            //System.out.println(s);
	        }
	        bReader.close();
	        return sb.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
