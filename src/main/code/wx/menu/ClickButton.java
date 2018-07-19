package wx.menu;

import lombok.Data;

/**
 * 
 * @ClassName: ClickButton
 * @Description: 点击事件按钮
 * @author chim
 * @date 2016年11月21日
 *
 */
@Data
public class ClickButton extends Button {
	private String key;
	private String type;

}
