package com.example.util;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLContentHandler extends DefaultHandler {
	private ExceptionInfo exception;
	private String currentTag ;
	private String temp="";

	public ExceptionInfo readXml(InputStream in)throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		sp.parse(in, this);
		return exception;
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String text = new String(ch,start,length);
		
		temp += text;
		
	}

	
	@Override
	public void startDocument() throws SAXException {
		exception = new ExceptionInfo();
	}


	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("restaurant-name".equals(localName)){
			exception.restaurant_name = temp;
		}else if("error-level".equals(localName)){
			exception.error_level = Integer.parseInt(temp);
		}else if("error-time".equals(localName)){
			exception.error_time = temp;
		}else if("error-cause".equals(localName)){
			exception.error_cause = temp;
		}
		temp = "";
		currentTag = null;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		currentTag = localName;
	}

}
