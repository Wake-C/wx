package com.wx.test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * 
* @ClassName: XstreamTest
* @Description: XstreamTest转换测试
* @author chim
* @date 2016年11月16日
*
 */
public class XstreamTest {
	public static void main(String[] args) {
		Person p = new Person();
		p.setName("测试xStream转换");
		p.setAge(1);
		p.setAddress("GD");
		String xml=javaObject2Xml(p);
		System.out.println(xml);
		
	    Person pp=(Person) xml2JavaObject(xml, p);
	    System.out.println(pp.getName()+"-"+pp.getAddress()+"-"+pp.getAge());
	}

	public static String javaObject2Xml(Object o) {
		XStream xt = new XStream(new DomDriver());
		xt.alias(o.getClass().getSimpleName(), o.getClass());
		return xt.toXML(o);
	}
	/**
	 * 
	* @Title: xml2JavaObject
	* @Description:  xml转换为java对象
	* @param @param xml  xml String
	* @param @param o   要转换的对象
	* @return Object    返回类型
	 */
	public static Object xml2JavaObject(String xml, Object o) {
		XStream xt = new XStream(new DomDriver());
		xt.alias(o.getClass().getSimpleName(), o.getClass());
		Object ob = xt.fromXML(xml);
		return ob;
	}

}
