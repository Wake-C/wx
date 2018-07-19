package wx.util;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wx.pojo.AuthUserInfo;
import wx.pojo.WeixinAuth2Token;

/**
 * 
 * @ClassName: AdvancedUtil
 * @Description: 微信高级接口工具
 * @author chim
 * @date 2016年11月29日
 *
 */
public class AdvancedUtil {
	private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);

	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 * @param appSecret
	 * @param code
	 * @return
	 */
	public static WeixinAuth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinAuth2Token authToken = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
		JSONObject resultJson = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (resultJson != null) {
			try {
				authToken = new WeixinAuth2Token();
				authToken.setAccessToken(resultJson.getString("access_token"));
				authToken.setExpiresIn(resultJson.getInt("expires_in"));
				authToken.setOpenId(resultJson.getString("openid"));
				authToken.setRefreshToken(resultJson.getString("refresh_token"));
				authToken.setScope(resultJson.getString("scope"));
			} catch (Exception e) {
				authToken = null;
				int errorcode = resultJson.getInt("errcode");
				String errorMsg = resultJson.getString("errmsg");
				log.error("获取网页授权凭证token失败errcode:{} errmsg:{}", errorcode, errorMsg);
			}
		}
		return authToken;
	}

	/**
	 * 刷新网页授权凭证
	 * 
	 * @param appId
	 * @param refeshToken
	 * @return
	 */
	public static WeixinAuth2Token RefeshAutho2AccessToken(String appId, String refeshToken) {
		WeixinAuth2Token authToken = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN ";
		requestUrl = requestUrl.replace("APPID", appId).replace("REFRESH_TOKEN", refeshToken);
		JSONObject resultJson = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (resultJson != null) {
			try {
				authToken = new WeixinAuth2Token();
				authToken.setAccessToken(resultJson.getString("access_token"));
				authToken.setExpiresIn(resultJson.getInt("expires_in"));
				authToken.setOpenId(resultJson.getString("openid"));
				authToken.setRefreshToken(resultJson.getString("refresh_token"));
				authToken.setScope(resultJson.getString("scope"));
			} catch (Exception e) {
				authToken = null;
				int errorcode = resultJson.getInt("errcode");
				String errorMsg = resultJson.getString("errmsg");
				log.error("刷新网页授权凭证token失败errcode:{} errmsg:{}", errorcode, errorMsg);
			}
		}
		return authToken;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param authAccessToken
	 * @param openId
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static AuthUserInfo getAuthUserInfo(String authAccessToken, String openId) {
		AuthUserInfo userInfo = null;
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + authAccessToken + "&openid="
				+ openId + "&lang=zh_CN";
		JSONObject resultJson = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (resultJson != null) {
			try {
				userInfo = new AuthUserInfo();
				// 用户的标识
				userInfo.setOpenId(resultJson.getString("openid"));
				// 昵称
				userInfo.setNickname(resultJson.getString("nickname"));
				// 性别（1是男性，2是女性，0是未知）
				userInfo.setSex(resultJson.getInt("sex"));
				// 用户所在国家
				userInfo.setCountry(resultJson.getString("country"));
				// 用户所在省份
				userInfo.setProvince(resultJson.getString("province"));
				// 用户所在城市
				userInfo.setCity(resultJson.getString("city"));
				// 用户头像
				userInfo.setHeadimgurl(resultJson.getString("headimgurl"));
				// 用户特权信息
				userInfo.setPrivilegeList(JSONArray.toList(resultJson.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				userInfo = null;
				int errorcode = resultJson.getInt("errcode");
				String errorMsg = resultJson.getString("errmsg");
				log.error("获取用户信息失败token失败errcode:{} errmsg:{}", errorcode, errorMsg);
			}

		}
		return userInfo;
	}
}
