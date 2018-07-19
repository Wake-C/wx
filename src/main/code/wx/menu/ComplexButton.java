package wx.menu;

import lombok.Data;

/**
 * 
 * @ClassName: ComplexButton
 * @Description: 复合按钮
 * @author chim
 * @date 2016年11月21日
 *
 */
@Data
public class ComplexButton extends Button {
	private Button[] sub_button;
	

}
