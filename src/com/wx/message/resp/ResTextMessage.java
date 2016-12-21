package com.wx.message.resp;

/**
 * 
 * @ClassName: ResBaseMessage
 * @Description: 回复文本消息 公众号--普通用户
 * @author chim
 * @date 2016年11月16日
 *
 */
public class ResTextMessage extends ResBaseMessage {
	private String Content;// 回复的消息内容

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
