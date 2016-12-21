package com.wx.pojo;
/**
 * 
* @ClassName: Token
* @Description: wx——token
* @author chim
* @date 2016年11月25日
*
 */
public class Token {
	private String accessToKen;
	private int expiresIn;  //单位秒

	public String getAccessToKen() {
		return accessToKen;
	}

	public void setAccessToKen(String accessToKen) {
		this.accessToKen = accessToKen;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

}
