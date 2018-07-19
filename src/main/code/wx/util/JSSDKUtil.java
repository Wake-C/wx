package wx.util;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wx.pojo.JsApiTicket;

/**
 * 
 * @ClassName: JSSDKUtil
 * @Description: 微信 JS-SDK工具类
 * @author chim
 * @date 2016年11月29日
 *
 */
public class JSSDKUtil {
	private static Logger log = LoggerFactory.getLogger(JSSDKUtil.class);

	/**
	 * 获取公众号用于调用微信JS接口的临时票据
	 * 
	 * @param accessToken
	 * @return
	 */
	public static JsApiTicket getJsapiTicket(String accessToken) {
		JsApiTicket jsTicket = null;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken
				+ "&type=jsapi";

		JSONObject resultJson = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (resultJson != null) {
			jsTicket = new JsApiTicket();
			jsTicket.setTicket(resultJson.getString("ticket"));
			jsTicket.setExpiresin(resultJson.getInt("expires_in"));
		}
		return jsTicket;
	}

	@Test
	public void test() {
		String accessToken = CommonUtil.getToken().getAccessToKen();
		System.out.println(accessToken);
		JsApiTicket jsTicket = getJsapiTicket(accessToken);
		System.out.println(jsTicket.getTicket());

	}
}
