package wx.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import wx.message.resp.Article;
import wx.message.resp.ResNewsMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: MessageUtil
 * @Description: 处理微信请求参数
 * @author chim
 * @date 2016年11月16日
 *
 */
public class MessageUtil {
	// 请求消息类型：文本
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	// 请求消息类型：图片
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	// 请求消息类型：语音
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	// 请求消息类型：视频
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	// 请求消息类型：地理位置
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	// 请求消息类型：链接
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	// 请求消息类型：事件推送
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	// 事件类型：subscribe(订阅)
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	// 事件类型：unsubscribe(取消订阅)
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// 事件类型：scan(用户已关注时的扫描带参数二维码)
	public static final String EVENT_TYPE_SCAN = "scan";
	// 事件类型：LOCATION(上报地理位置)
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	// 事件类型：CLICK(自定义菜单)
	public static final String EVENT_TYPE_CLICK = "CLICK";

	// 响应消息类型：文本
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	// 响应消息类型：图片
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	// 响应消息类型：语音
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	// 响应消息类型：视频
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	// 响应消息类型：音乐
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	// 响应消息类型：图文
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * @Title: parseXml
	 * @Description: 将微信请求转换为map
	 * @return Map<String,String> 返回类型
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 解析结果存储map
		Map<String, String> map = new HashMap<String, String>();

		InputStream input = request.getInputStream();

		SAXReader reader = new SAXReader();
		Document document = reader.read(input);
		Element root = document.getRootElement();
		List<Element > elements = root.elements();
		// 把所有xml节点放入map
		for (Element e : elements) {
			map.put(e.getName(), e.getText());
		}
		input.close();
		input = null;
		return map;
	}
	
	public static Map<String, String> parseXml(String rxml) throws Exception {
		// 解析结果存储map
		Map<String, String> map = new HashMap<String, String>();

		Document document = DocumentHelper.parseText(rxml);
		Element root = document.getRootElement();
		List<Element > elements = root.elements();
		// 把所有xml节点放入map
		for (Element e : elements) {
			map.put(e.getName(), e.getText());
		}
		return map;
	}

	// 扩展xstram 使其支持cdata
	private static XStream xstream = new XStream( new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 所有节点转换都加入cdata
				boolean cdata = true;

				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
	/**
	 *  @Title: messageToxml
	 *  @Description: 对象转换为xml 
	 *  @return String 返回类型 xml
	 */
	public static String messageToxml(Object obj) {
		xstream.alias("xml", obj.getClass());
		return xstream.toXML(obj);
	}

	public static String newsMessageToxml(ResNewsMessage s) {
		xstream.alias("xml", s.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(s);
	}
	
	public static String mapToXml(Map<Object, Object> map) {
		StringBuffer sb =new StringBuffer();
		sb.append("<xml>");
		if(map!=null && map.size()>0){
			for(Map.Entry<Object, Object> entry : map.entrySet()){
				sb.append("<"+entry.getKey()+"><![CDATA["+entry.getValue()+"]]></"+entry.getKey()+">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
		
	}
}
