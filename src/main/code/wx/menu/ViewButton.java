package wx.menu;

import lombok.Data;

/**
 * 
 * @ClassName: ViewButton
 * @Description: 跳转页面按钮
 * @author chim
 * @date 2016年11月21日
 *
 */
@Data
public class ViewButton extends Button {
	private String url;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
