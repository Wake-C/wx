package com.wx.message.req;

/**
 * 
* @ClassName: TextMessage
* @Description: 请求消息 用户-公众号 文本消息
* @author chim
* @date 2016年11月16日
*
 */
public class TextMessage extends BaseMessage {
	private String Content; // 消息内容

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	

}
