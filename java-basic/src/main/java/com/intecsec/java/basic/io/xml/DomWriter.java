package com.intecsec.java.basic.io.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomWriter {

	public static void main(String[] args) {
		
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			
			//新创建一个Document节点
			Document document = dbBuilder.newDocument();
			if (document != null) 
			{
				Element docx = document.createElement("document");	//都是采用Document创建元素		
				Element element = document.createElement("element");
				element.setAttribute("type", "paragraph"); 
				element.setAttribute("alignment", "left"); //element增加2个属性
				
				Element object = document.createElement("object");
				object.setAttribute("type", "text");
				
				Element text = document.createElement("text");
				text.appendChild(document.createTextNode("abcdefg")); //给text节点赋值
				Element bold = document.createElement("bold");
				bold.appendChild(document.createTextNode("true"));    //给bold节点赋值
				
				object.appendChild(text);      //把text节点挂在object下
				object.appendChild(bold);      //把bold节点挂在object下
				element.appendChild(object);   //把object节点挂在element下
				docx.appendChild(element);	   //把element节点挂在docx下		
				document.appendChild(docx);    //把docx挂在document下
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(document);
				
				//定义目标文件
				File file = new File("java-basic/files/xml/dom_result.xml");
				StreamResult result = new StreamResult(file);
		 	 
				//将xml内容写入到文件中
				transformer.transform(source, result);
				
				System.out.println("write xml file successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
}
