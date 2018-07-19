package wx.util;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wx.menu.Menu;

/**
 * 
* @ClassName: MenuUtil
* @Description: 微信菜单工具类
* @author chim
* @date 2016年11月28日
*
 */
public class MenuUtil {
	private final static Logger log             = LoggerFactory.getLogger(MenuUtil.class);
	// 菜单创建post
	private final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 菜单获取get
	private final static String menu_get_url    = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 菜单删除get
	private final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 * @param accessToken
	 *            微信凭证
	 * @return
	 */
	public static boolean createMenu(Menu menu, String accessToken) {
		boolean result = false;
		String outputjson = JSONObject.fromObject(menu).toString();
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject resultJson = CommonUtil.httpsRequest(url, "POST", outputjson);

		if (resultJson != null) {
			if (resultJson.getInt("errcode") == 0) {
				result = true;
			} else {
				log.error("{创建菜单失败errcode:" + resultJson.getString("errcode") + ",errmsg:"
						+ resultJson.getString("errmsg") + "}");
			}
		}
		return result;
	}

	/**
	 * 获取所有微信菜单
	 * 
	 * @param accessToken
	 * @return String json.toString
	 */
	public static String getMenu(String accessToken) {
		String url = menu_get_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject resultJson = CommonUtil.httpsRequest(url, "GET", null);
		if (resultJson != null) {
			return resultJson.toString();
		}
		return null;
	}

	/**
	 * 删除所有菜单
	 * 
	 * @param accessToken
	 * @return
	 */
	public static boolean deleteMenu(String accessToken) {
		boolean result = false;
		String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject resultJson = CommonUtil.httpsRequest(url, "GET", null);
		if (resultJson != null) {
			if (resultJson.getInt("errcode") == 0) {
				result = true;
			} else {
				log.error("{删除菜单失败errcode:" + resultJson.getString("errcode") + ",errmsg:"
						+ resultJson.getString("errmsg") + "}");
			}
		}
		return result;
	}
}
