package wx.menu;

import lombok.Data;

/**
 * 
 * @ClassName: 小程序按钮
 * @Description: 小程序按钮
 * @author chim
 * @date 2016年11月21日
 *
 */
@Data
public class MiniProgameButton extends Button {
	private String url;
	private String type;
	private String appid;
	private String pagepath;
}
