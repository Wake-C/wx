package com.wx.test;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jTest {

	public static void main(String[] args) throws Exception {
		StringBuilder buider = new StringBuilder();
		buider.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buider.append("<person>");
		buider.append("<name>测试</name>");
		buider.append("<age>25</age>");
		buider.append("<address>E</address>");
		buider.append("</person>");
        
		Document document = DocumentHelper.parseText(buider.toString());
		Element root=document.getRootElement();
		List<Element> list=  root.elements();
		for(Element e:list){
			System.out.println(e.getName()+e.getText());
		}
	}

}
