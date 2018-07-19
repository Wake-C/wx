package wx.test;

import net.sf.json.JSONObject;
import wx.util.MyX509TrustManager;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;

/**
 * 
 * @ClassName: TokenTest
 * @Description: 获取Token
 * @author chim
 * @date 2016年11月17日
 *
 */
public class TokenTest {
	public static void main(String[] args) throws Exception {
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx4057676eb20bc7c8&secret=421a3bb746b724674030ff058a226cda";

		URL url = new URL(tokenUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		// 自定义信任管理器
		TrustManager[] tm = { (TrustManager) new MyX509TrustManager() };

		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		conn.setSSLSocketFactory(ssf);
		conn.setDoInput(true);

		conn.setRequestMethod("GET");

		InputStream inputStream = conn.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

		BufferedReader bufferdRead = new BufferedReader(reader);

		StringBuffer buffer = new StringBuffer();
		String str = null;
		while ((str = bufferdRead.readLine()) != null) {
			buffer.append(str);
		}
		bufferdRead.close();
		reader.close();
		inputStream.close();
		conn.disconnect();
		JSONObject jso = JSONObject.fromObject(buffer.toString());
		System.out.println(jso.get("access_token") + "有效期" + jso.get("expires_in"));
	}
	
}
