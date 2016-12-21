package com.wx.message.resp;

/**
 * 
 * @ClassName: Image
 * @Description: 图片model
 * @author chim
 * @date 2016年11月16日
 *
 */
public class Image {
	private String meidaId; //通过素材管理中的接口上传多媒体文件，得到的id。

	public String getMeidaId() {
		return meidaId;
	}

	public void setMeidaId(String meidaId) {
		this.meidaId = meidaId;
	}

}
