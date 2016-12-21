package com.wx.pojo;

/**
 * @ClassName: JsApiTicket
 * @Description: 公众号用于调用微信JS接口的临时票据
 * @author chim
 * @date 2016年11月29日
 */
public class JsApiTicket {
	private String ticket;
	private int expiresin;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpiresin() {
		return expiresin;
	}

	public void setExpiresin(int expiresin) {
		this.expiresin = expiresin;
	}

}
