package com.wx.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/**
 * 微信验证签名工具类
 * 
 * @author Administrator
 *
 */
public class SignUtil {
	private static Logger log = LoggerFactory.getLogger(SignUtil.class);
	private static String token = "Roy";
	private final static char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	// sha1散列核对
	public static boolean checkString(String signature, String timestamp, String nonce) {
		String[] arry = new String[] { token, timestamp, nonce };
		Arrays.sort(arry);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arry.length; i++) {
			sb.append(arry[i]);
		}
		MessageDigest md = null;
		String temStr = null;

		try {
			md = MessageDigest.getInstance("SHA1");
			md.update(sb.toString().getBytes());
			byte[] s = md.digest();
			temStr = byteToString(s);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		sb = null;
		return temStr != null ? temStr.equals(signature.toUpperCase()) : false;

	}

	private static String byteToString(byte[] s) {
		String strDigest = "";
		for (int i = 0; i < s.length; i++) {
			strDigest += byteToHexStr(s[i]);
		}
		return strDigest;
	}

	// 16位加密
	private static String byteToHexStr(byte b) {
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(b >>> 4) & 0X0F];
		tempArr[1] = Digit[b & 0X0F];

		String s = new String(tempArr);
		return s;
	}

	/**
	 * 生成一个js接口调用加密签名
	 * @param jsapiTicket
	 *            微信JS接口ticket
	 * @param url
	 *            要调用js接口的页面的完整URL
	 * @return
	 */
	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapiTicket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}