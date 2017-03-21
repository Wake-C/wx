package com.wx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wx.pojo.Token;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: CommonUtil
 * @Description: 微信工具类
 * @author chim
 * @date 2016年11月25日
 *
 */
public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	// WX access_token
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String appid = "wx7179cc98fb47eff5";
	public final static String appsecret = "823ae7209cb695d4a4a0c71071d6006e";
	private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
    private final static String DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * 通用发送https请求
	 * 
	 * @param requestUrl
	 *            请求的Url
	 * @param requestMethod
	 *            请求方式
	 * @param outputStr
	 *            提交数据
	 * @return
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);

			if (outputStr != null) {
				OutputStream output = conn.getOutputStream();
				output.write(outputStr.getBytes("UTF-8"));
				output.close();
			}
			InputStream input = conn.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(input, "utf-8");
			BufferedReader buffered = new BufferedReader(inputReader);
			String str = null;
			StringBuilder strbuilder = new StringBuilder();
			if ((str = buffered.readLine()) != null) {
				strbuilder.append(str);
			}
			buffered.close();
			inputReader.close();
			input.close();
			input = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(strbuilder.toString());

		} catch (ConnectException e) {
			log.error("连接超时");
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		}

		return jsonObject;
	}
	
	
	public static String postData(String urlStr, String data, String contentType){
		BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if(contentType != null)
                conn.setRequestProperty("content-type", contentType);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if(data == null)
                data = "";
            writer.write(data); 
            writer.flush();
            writer.close();  

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            log.error("Error connecting to " + urlStr + ": " + e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
            }
        }
        return null;
	}

	/**
	 * 获取wx接口调用凭证
	 * 
	 * @param appid
	 *            微信帐号凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static Token getToken(String appid, String appsecret) {
		Token token = null;
		String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject json = httpsRequest(requestUrl, "GET", null);
		if (json != null)
			try {
				token = new Token();
				token.setAccessToKen(json.getString("access_token"));
				token.setExpiresIn(json.getInt("expires_in"));
			} catch (JSONException e) {
				log.error("获取token失败 errcode:{} errmsg:{}", json.getInt("errcode"), json.getString("errmsg"));
			}

		return token;
	}

	/**
	 * URL编码
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodingUTF8(String source) {
		String result = source;
		try {
			result = URLEncoder.encode(result, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 生成授权地址
	 * 
	 * @param url
	 *            授权重定向地址
	 * @param Scope
	 *            授权类型 snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid） snsapi_userinfo
	 *            （弹出授权页面，可通过openid拿到昵称、性别、所在地等
	 * @return
	 */
	public static String getAuthUrl(String url, String Scope) {
		// 授权转发地址
		String authRedirectUrl = "http://rsayy.free.natapp.cc/wx/oauth";
		String authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + CommonUtil.appid
				+ "&redirect_uri=" + CommonUtil.urlEncodingUTF8(authRedirectUrl)
				+ "&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		authUrl = authUrl.replace("SCOPE", Scope);
		System.out.println(authUrl);
		return authUrl;
	}
}
