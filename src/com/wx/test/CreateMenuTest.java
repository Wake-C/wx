package com.wx.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.wx.menu.Button;
import com.wx.menu.ClickButton;
import com.wx.menu.ComplexButton;
import com.wx.menu.Menu;
import com.wx.menu.ViewButton;
import com.wx.util.MyX509TrustManager;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: CreateMenuTest
 * @Description: 按钮创建
 * @author chim
 * @date 2016年11月21日
 *
 */
public class CreateMenuTest {
	public static void main(String[] args) throws Exception {
		String menuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

		menuUrl = menuUrl.replace("ACCESS_TOKEN",
				"cLIz_syUgdJlJFS0QsMKlW_VDlKDcwM7p1SDIgJGJizK4RiZzfeTDqW4yBuiupzIxN31LMOWVkKrve1tpO9dC8oNNboXEOCmew2RqkuyVnrGI67_M6TA8M50Nd9fgZ0eTABjAIABIJ");
		URL url = new URL(menuUrl);
		HttpsURLConnection httpConn = (HttpsURLConnection) url.openConnection();

		// 设置SSL
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		httpConn.setSSLSocketFactory(ssf);
		// 设置HTTP可读可写
		httpConn.setDoInput(true);
		httpConn.setDoOutput(true);
		httpConn.setRequestMethod("POST");
		// 写入菜单
		OutputStream output = httpConn.getOutputStream();
		output.write(JSONObject.fromObject(getMenu()).toString().getBytes("UTF-8"));
		output.close();

		// 获取结果
		InputStream input = httpConn.getInputStream();
		InputStreamReader inputReader = new InputStreamReader(input, "UTF-8");
		BufferedReader bufferRead = new BufferedReader(inputReader);
		StringBuilder builder = new StringBuilder();
		String str = null;
		while ((str = bufferRead.readLine()) != null) {
			builder.append(str);
		}

		bufferRead.close();
		inputReader.close();
		input.close();
		httpConn.disconnect();
		System.out.println(builder);
	}

	public static Menu getMenu() {
		ClickButton ck1 = new ClickButton();
		ck1.setName("joke");
		ck1.setType("click");
		ck1.setKey("88");

		ClickButton sck1 = new ClickButton();
		sck1.setName("moive");
		sck1.setType("click");
		sck1.setKey("99");

		ClickButton sck2 = new ClickButton();
		sck2.setName("music");
		sck2.setType("click");
		sck2.setKey("100");
		

		ViewButton vb1 = new ViewButton();
		vb1.setName("网址导航");
		vb1.setType("view");
		vb1.setUrl("http://123456iloveyoulong.com/");
		
		ViewButton vb2 = new ViewButton();
		vb2.setName("八百方测试");
		vb2.setType("view");
		vb2.setUrl("http://aj1wf.free.natapp.cc/shop/m/carAds/carAds.html");
		
		ViewButton vb3 = new ViewButton();
		vb3.setName("微信分享测试");
		vb3.setType("view");
		vb3.setUrl("http://aj1wf.free.natapp.cc/shop/m/carAds/carAds2.html");

		ComplexButton cmb = new ComplexButton();
		cmb.setName("娱乐");
		cmb.setSub_button(new Button[] { sck1, sck2 ,vb2,vb3});

		Menu menu = new Menu();
		menu.setButton(new Button[] { ck1, vb1, cmb });

		return menu;
	}
}
