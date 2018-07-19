package wx.controller;

import wx.pojo.AuthUserInfo;
import wx.pojo.WeixinAuth2Token;
import wx.util.AdvancedUtil;
import wx.util.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuthServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("gb2312");
		resp.setCharacterEncoding("gb2312");

		String code = req.getParameter("code");
		if (!"authdeny".equals(code)) {
			// 网页授权accessToken
			WeixinAuth2Token authToken = AdvancedUtil.getOauth2AccessToken(CommonUtil.appid, CommonUtil.appsecret,
					code);
			// 网页授权接口访问凭证
			String authAccess = authToken.getAccessToken();
			// 用户关联公众号id
			String openId = authToken.getOpenId();
			AuthUserInfo userInfo = AdvancedUtil.getAuthUserInfo(authAccess, openId);

			req.setAttribute("userInfo", userInfo);
		}
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

}
