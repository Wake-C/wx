package com.wx.menu;

/**
 * 
 * @ClassName: ClickButton
 * @Description: 点击事件按钮
 * @author chim
 * @date 2016年11月21日
 *
 */
public class ClickButton extends Button {
	private String key;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
