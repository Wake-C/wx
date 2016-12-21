package com.wx.message.resp;

/**
 * 
 * @ClassName: ResBaseMessage
 * @Description: 响应消息基类 公众号--普通用户
 * @author chim
 * @date 2016年11月16日
 *
 */
public class ResBaseMessage {
	private String ToUserName; // 接收方帐号（收到的OpenID）
	private String FromUserName; // 开发者微信号
	private Long CreateTime; // 消息创建时间 （整型）
	private String MsgType; // 消息类型text music news
	private String Content; // 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
