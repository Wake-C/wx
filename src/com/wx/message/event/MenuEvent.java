package com.wx.message.event;

/**
 * 
 * @ClassName: MenuEvent
 * @Description: 自定义菜单事件
 * @author chim
 * @date 2016年11月16日
 *
 */
public class MenuEvent extends BaseEvent {
	private String EventKey;// 事件KEY值，与自定义菜单接口中KEY值对应

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
