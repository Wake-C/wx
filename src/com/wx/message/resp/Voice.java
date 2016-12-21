package com.wx.message.resp;

/**
 * 
 * @ClassName: Voice
 * @Description: 语音model
 * @author chim
 * @date 2016年11月16日
 *
 */
public class Voice {
	private String MediaId;// 通过素材管理中的接口上传多媒体文件，得到的id。

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	
}
