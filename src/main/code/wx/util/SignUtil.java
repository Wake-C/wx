package wx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 微信验证签名工具类
 * @author chim
 * @date  2017年3月16日 16:26:38
 */
public class SignUtil {
	private static       Logger log   = LoggerFactory.getLogger(SignUtil.class);
	private static       String token = "River";   //验证的token
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
	 * MD5 十六进制加密
	 * @param origin
	 * @param charsetname
	 * @return
	 */
	 public static String MD5Encode(String origin, String charsetname) {  
	        String resultString = null;  
	        try {  
	            resultString = new String(origin);  
	            MessageDigest md = MessageDigest.getInstance("MD5");  
	            if (charsetname == null || "".equals(charsetname))  
	                resultString = byteArrayToHexString(md.digest(resultString  
	                        .getBytes()));  
	            else  
	                resultString = byteArrayToHexString(md.digest(resultString  
	                        .getBytes(charsetname)));  
	        } catch (Exception exception) {  
	        }  
	        return resultString;  
	    }  
	 
	 private static String byteArrayToHexString(byte b[]) {  
	        StringBuffer resultSb = new StringBuffer();  
	        for (int i = 0; i < b.length; i++)  
	            resultSb.append(byteToHexStr(b[i]));  
	  
	        return resultSb.toString();  
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

	public static String create_nonce_str() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	/**
	 * 微信sign和本地加密对比
	 * @param sort 
	 * @param api_key  支付密钥
	 */
	public static boolean isWxSign(SortedMap<String, String> sort,String api_key) {
		StringBuffer sb =new StringBuffer();
		for(Map.Entry<String, String> entry : sort.entrySet()){
			String key  =entry.getKey();
			String value=entry.getValue();
			if(!"sign".equals(key) && value !=null && !"".equals(value) ){
				sb.append(key+"="+value+"&");
			}
		}
		sb.append("key="+api_key);
		return sort.get("sign").equals(MD5Encode(sb.toString(), "UTF-8"));
	}
	
	/**
	 * 微信支付sign
	 * @param characterEncoding
	 * @param sortMap
	 * @param API_KEY 支付密钥
	 * @return
	 */
	public static String createSign(String characterEncoding, SortedMap<Object, Object> sortMap, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		for(Map.Entry<Object, Object> entry :sortMap.entrySet()){
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		String sign = SignUtil.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}
}