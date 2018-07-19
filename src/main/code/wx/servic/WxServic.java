package wx.servic;

import wx.message.req.TextMessage;
import wx.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class WxServic {
	/**
	 * @Title: processRequest
	 * @Description: 处理微信请求 返回xml
	 * @return String 返回类型 xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// 返回xml
		String resXml = null;
		// 默认返回文本信息
		String resContent = "未知消息无法解析";
		try {
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 发送方 openId
			String fromUserName = requestMap.get("FromUserName");
			// 消息创建时间 1970 零时到创建时毫秒
			String createTime = requestMap.get("CreateTime");
			// 消息类型 text image voice location video link
			String msgType = requestMap.get("MsgType");
			// 消息 Id
			String msgId = requestMap.get("MsgId");
			
			//简单测试回复一个文本信息
			TextMessage text =new TextMessage();
			text.setToUserName(fromUserName);
			text.setFromUserName(toUserName);
			text.setCreateTime(new Date().getTime());
			text.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
			

			// 各种类型消息处理
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 消息内容
				String content = requestMap.get("Content");
				// 文本消息处理
				resContent="不要重复输入";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				// 图片Url
				String picUrl = requestMap.get("PicUrl");
				//  图片消息处理
				resContent="测试图文";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				// 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
				String mediaId = requestMap.get("MediaId");
				// 语音格式 AMR
				String format = requestMap.get("Format");
				// 语音识别结果，UTF8编码
				String recognition = requestMap.get("Recognition");
				//  语音消息处理
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
				String mediaId = requestMap.get("MediaId");
				// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
				String thumbMediaId = requestMap.get("ThumbMediaId");
				//  视频消息处理
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// 地理位置维度
				String location_X = requestMap.get("Location_X");
				// 地理位置经度
				String location_Y = requestMap.get("Location_Y");
				// 地图缩放大小
				String lcale = requestMap.get("Scale");
				// 地理位置信息
				String label = requestMap.get("Label");
				//  位置消息处理
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				// 消息标题
				String title = requestMap.get("Title");
				// 消息描述
				String description = requestMap.get("Description");
				// 消息链接
				String url = requestMap.get("Url");
				//  链接消息处理
			} 
			//事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { 
				 //事件类型
				String eventType=requestMap.get("Event");
				//触发key
				String key=requestMap.get("EventKey");
				//关注时
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					resContent="欢迎关注测试号";
				}//取消关注时
				else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
					
				}
				//扫描二维码时
				else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
					
				}
				//上报地理位置
				else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
					
				}
				//自定义菜单
				else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
					if(key.equals("-1")){
						resContent="10°~15°";
					}else if(key.equals("-2")){
						Random rd = new Random();
						int i = rd.nextInt(1000);
						resContent=""+i;
					}else if(key.equals("88")){

					    resContent="i m your father";
					}
				}
			}
			//添加回复文本  转换xml
			 text.setContent(resContent);
			 resXml= MessageUtil.messageToxml(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return resXml;

	}

}
