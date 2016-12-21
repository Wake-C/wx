package com.wx.message.resp;

/**
 * 
 * @ClassName: ImageMessage
 * @Description: 回复图片消息 公众号--普通用户
 * @author chim
 * @date 2016年11月16日
 *
 */
public class ResImageMessage extends ResBaseMessage {
	private Image Image; // 图片

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

}
