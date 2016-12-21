package com.wx.message.resp;

/**
 * 
 * @ClassName: ResVideoMessage
 * @Description: 回复视频消息 公众号--普通用户
 * @author chim
 * @date 2016年11月16日
 *
 */
public class ResVideoMessage extends ResBaseMessage {
	private Video Video; // 视频

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

}
