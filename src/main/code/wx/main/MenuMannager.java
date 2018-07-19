package wx.main;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wx.menu.*;
import wx.pojo.Token;
import wx.util.CommonUtil;
import wx.util.MenuUtil;

/**
 * 
 * @ClassName: MenuMannager
 * @Description: 微信菜单管理
 * @author chim
 * @date 2016年11月28日
 *
 */
public class MenuMannager {
	private static Logger log = LoggerFactory.getLogger(MenuMannager.class);

	public static void main(String[] args) {
		Token token = CommonUtil.getToken();
		String accessToken = token.getAccessToKen();
		boolean result = MenuUtil.createMenu(getMenu(), accessToken);
		if (result) {
			log.info("菜单创建成功");
			System.out.println("菜单创建成功");
		} else {
			log.info("菜单创建失败");
		}
	}

	@Test
	public void test() {

	}

	/**
	 * 微信菜单组装
	 * 
	 * @return 菜单
	 */
	public static Menu getMenu() {
		

		// 第一组菜单
		ViewButton viewButton = new ViewButton();
		viewButton.setName("today");
		viewButton.setType("view");
		viewButton.setUrl("http://www.todayonhistory.com/");

		ViewButton viewButton2 = new ViewButton();
		viewButton2.setName("万年历");
		viewButton2.setType("view");
		viewButton2.setUrl("http://hao.360.cn/rili/");

		ComplexButton complexButton1 = new ComplexButton();
		complexButton1.setName("生活");
		complexButton1.setSub_button(new Button[] { viewButton, viewButton2 });

		// 第二组菜单
		ClickButton clickButton1 = new ClickButton();
		clickButton1.setName("天气");
		clickButton1.setType("click");
		clickButton1.setKey("-1");

		ClickButton clickButton2 = new ClickButton();
		clickButton2.setName("抽签");
		clickButton2.setType("click");
		clickButton2.setKey("-2");

		ComplexButton complexButton2 = new ComplexButton();
		complexButton2.setName("OOOOO");
		complexButton2.setSub_button(new Button[] { clickButton1, clickButton2 });

		// 第三组菜单
		// 授权地址
		String authURL=CommonUtil.getAuthUrl("http://rsayy.free.natapp.cc/wx/oauth", "snsapi_userinfo");
		ViewButton viewButton3 = new ViewButton();
		viewButton3.setName("授权我");
		viewButton3.setType("view");
		viewButton3.setUrl(authURL);

		ViewButton viewButton4 = new ViewButton();
		viewButton4.setName("分享一下");
		viewButton4.setType("view");
		viewButton4.setUrl("http://rsayy.free.natapp.cc/wx/share.jsp");

		ViewButton viewButton5 = new ViewButton();
		viewButton5.setName("800方");
		viewButton5.setType("view");
		viewButton5.setUrl("https://www.800pharm.com/");

		ComplexButton complexButton3 = new ComplexButton();
		complexButton3.setName("测试");
		complexButton3.setSub_button(new Button[] { viewButton3, viewButton4, viewButton5 });
		// 加入菜单
		Menu menu = new Menu();
		menu.setButton(new Button[] { complexButton1, complexButton2, complexButton3 });
		return menu;
	}
}
