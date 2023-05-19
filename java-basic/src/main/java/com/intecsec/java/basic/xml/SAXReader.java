package com.intecsec.java.basic.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXReader {
	public static void main(String[] args) throws SAXException, IOException {
		XMLReader parser = XMLReaderFactory.createXMLReader();
		BookHandler bookHandler = new BookHandler();
		parser.setContentHandler(bookHandler);
		parser.parse("java-basic/xml/books.xml");
		System.out.println(bookHandler.getNameList());
	}
}

class BookHandler extends DefaultHandler {
	private List<String> nameList;
	private boolean title = false;

	public List<String> getNameList() {
		return nameList;
	}

	// xml文档加载时
	public void startDocument() throws SAXException {
		System.out.println("Start parsing document...");
		nameList = new ArrayList<String>();
	}

	// 文档解析结束
	public void endDocument() throws SAXException {
		System.out.println("End");
	}

	// 访问某一个元素
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

		if (qName.equals("title")) {
			title = true;
		}
	}

	// 结束访问元素
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		// End of processing current element
		if (title) {
			title = false;
		}
	}

	// 访问元素正文
	public void characters(char[] ch, int start, int length) {
		
		if (title) {
			String bookTitle = new String(ch, start, length);
			System.out.println("Book title: " + bookTitle);
			nameList.add(bookTitle);
		}
	}

}
