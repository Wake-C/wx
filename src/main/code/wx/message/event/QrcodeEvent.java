package wx.message.event;

/**
 * @ClassName: QrcodeEvent
 * @Description: 扫描带参数二维码
 * @author chim
 * @date 2016年11月16日
 *
 */
public class QrcodeEvent extends BaseEvent {
	private String EventKey;// 事件KEY值
	private String Ticket;// 二维码的ticket，可用来换取二维码图片

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

}
