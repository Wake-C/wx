package com.wx.message.req;

/**
 * 
 * @ClassName: ImageMessage
 * @Description: 请求消息 用户-公众号 图片
 * @author chim
 * @date 2016年11月16日
 *
 */
public class ImageMessage extends BaseMessage {
	private String PicUrl; // 图片Url

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}
