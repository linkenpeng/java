package com.intecsec.java.basic.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * dom 需要把整个文件加载到内存
 */
public class DomReader 
{
	public static void main(String[] a)
	{
        String fileName = "java-basic/xml/users.xml";
		recursiveTraverse(fileName); //自上而下进行访问
		System.out.println("========华丽丽的分割线==========");
		traverseBySearch(fileName);    //根据名称进行搜索
	}
    public static void recursiveTraverse(String fileName)
    {
    	try 
    	{
    		//采用Dom解析xml文件
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            
            //获取所有的一级子节点
            NodeList usersList = document.getChildNodes();
            System.out.println(usersList.getLength());  //1  
            
            for (int i = 0; i < usersList.getLength(); i++) 
            {
                Node users = usersList.item(i);         //1  users 
                
                NodeList userList = users.getChildNodes(); //获取二级子节点user的列表
                System.out.println("==" + userList.getLength()); //9
                
                for (int j = 0; j < userList.getLength(); j++) //9
                {
                    Node user = userList.item(j);
                    if (user.getNodeType() == Node.ELEMENT_NODE)
                    {
                    	 NodeList metaList = user.getChildNodes();
                         System.out.println("====" + metaList.getLength()); //7
                         
                         for (int k = 0; k < metaList.getLength(); k++) //7
                         {
                         	//到最后一级文本
                        	Node meta = metaList.item(k);
                        	if (meta.getNodeType() == Node.ELEMENT_NODE)
                        	{
                        		System.out.println(metaList.item(k).getNodeName() 
                        				+ ":" + metaList.item(k).getTextContent());
                        	}                                                              
                         }                    
                         System.out.println();
                    }                   
                }
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }   
    
    public static void traverseBySearch(String fileName)
    {
    	try 
    	{
    		//采用Dom解析xml文件
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            
            Element rootElement = document.getDocumentElement();         

            NodeList nodeList = rootElement.getElementsByTagName("name"); 
            if(nodeList != null) 
            { 
               for (int i = 0 ; i < nodeList.getLength(); i++) 
               { 
                  Element element = (Element)nodeList.item(i);                  
                  System.out.println(element.getNodeName() + " = " + element.getTextContent());
               } 
            }             
        } catch (Exception e) {
            e.printStackTrace();
        } 	
    }       
}