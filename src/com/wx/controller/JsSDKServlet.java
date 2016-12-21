package com.wx.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.util.CommonUtil;
import com.wx.util.JSSDKUtil;
import com.wx.util.SignUtil;

import net.sf.json.JSONObject;

/**
 * 
* @ClassName: JsSDKServlet
* @Description: 微信JSSDK
* @author chim
* @date 2016年11月29日
*
 */
@WebServlet("/JsSDKServlet")
public class JsSDKServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//微信接口凭证
		String accessToken=CommonUtil.getToken(CommonUtil.appid, CommonUtil.appsecret).getAccessToKen();
		//微信Jssdk接口凭证
		String jsTicket =JSSDKUtil.getJsapiTicket(accessToken).getTicket();
		//要分享的地址
		String url=request.getParameter("currentUrl");
		url=url.replaceAll("___", "&");
		Map<String, String> map=SignUtil.sign(jsTicket, url);
		map.put("appId", CommonUtil.appid);
		/*for(Entry<String, String> str :map.entrySet()){
			request.setAttribute(str.getKey(), str.getValue());
		}
		request.getRequestDispatcher("share.jsp").forward(request, response);*/
		
		JSONObject object =JSONObject.fromObject(map);
		response.getWriter().write(object.toString());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
