package com.wx.menu;

/**
 * 
 * @ClassName: ComplexButton
 * @Description: 复合按钮
 * @author chim
 * @date 2016年11月21日
 *
 */
public class ComplexButton extends Button {
	private Button[] sub_button;
	

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}
