package jm.frameWork.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import jm.basic.util.CommonUtils;
import jm.basic.util.ConstantPool;
import jm.frameWork.util.xcx.XcxConstant;

public class WxUtils {
	//8e6c09be86d0451c6d08215e84da61ac  产融家
	//1e1ff8db563d337ad4551c939324c195   优董俱乐部

	// 全局access_token的url
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 获取jsapi_ticket的url
	public final static String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	// 网页授权获取用户code的url
	public final static String GETCODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=returnURL";
	// 根据code值获取网页access_token的url
	public final static String getOpenIdAfter_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	// 获取用户信息url
	public final static String GETINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 获取会员信息详情
	 */
	public final static String GET_MEMBER_INFO="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

//	/**
//	 * 支付系统： 微信商户号
//	 */
//	public final static String WXSH = "1313436601";
//	// 微信商户支付APP密钥
//	public final static String WXZF_KEY = "BYyGiMcbctIjr8LO0pCDK42zI7jlHDtB";
//	// 微信支付成功后回调地址
//	public final static String NOTIFY_URL = ConstantPool.DOMAIN + "/memberwxzf_userWxzfRespone.action";
//	
//	// 微信支付回调地址
//	public final static String PAY_BAK_URL = ConstantPool.DOMAIN + "/memberPay_memberWXPayCallback.action";
//	
//	// 微信支付接口连接
//	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//
//	// 任务微信推送的消息模板
//	public static final String msgTempId = "QomjlOzzyJeW-Ju1SsPZWPv-bgFWMrXUryzTfyi4UDw";
//
//	// 报名审核结果通知模板id
//	public static final String msgSignUpId = "E-Or1ajIkDJaXkEJYPxpnF2czAYsbrXleP78vjxkebk";
//	// 数据提交提醒模板id
//	public static final String msgDataSubmitId = "Jr79S36GMIjozfX1uQbrN0p_xm--2UGWVBW2VWBNqU8";
//
//	// 推荐活动入群等审核模板id
//	public static final String msgRecommendId = "RdsMuR67WHBszM7gyMxxmNCTjD3hYArKwqGEMapVli4";
//	// 测试模板id
//	public static final String TempIdmsg = "fZA9-XnfocisvLbyyX9lNIEM-UOhZ5wjOPtzVtBhcxM";
//	// 微信签名方式
//	public final static String SIGN_TYPE = "MD5";// 签名加密方式

	/**
	 * 获取全局access_token 项目启动时调用此方法往缓存中保存数据
	 * 
	 * @return
	 */
	public static String getAccess_token() {
		String access_token = PublishCache.access_token.get("access_token");
		Long time = System.currentTimeMillis();
		if (null == access_token || "".equals(access_token)
				|| "null".equals(access_token)) {
			String url = WxUtils.ACCESS_TOKEN_URL.replace("APPID",
					XcxConstant.APPID).replace("APPSECRET", XcxConstant.APPSECRET);
			JSONObject jsonObject = JSONObject.parseObject(httpsRequest(url,
					"GET", null));
			if (null != jsonObject) {
				access_token = jsonObject.getString("access_token") + ";"
						+ time;
				PublishCache.access_token.put("access_token", access_token);
			}
		} else {
			String TimeTemp = access_token.split(";")[1];
			if (time - Long.valueOf(TimeTemp) > 7000000) {
				String url = WxUtils.ACCESS_TOKEN_URL.replace("APPID",
						XcxConstant.APPID).replace("APPSECRET", XcxConstant.APPSECRET);
				JSONObject jsonObject = JSONObject.parseObject(httpsRequest(url,
						"GET", null));
				if (null != jsonObject) {
					access_token = jsonObject.getString("access_token") + ";"
							+ time;
					PublishCache.access_token.put("access_token", access_token);
				}
			}

		}
		// 刷新 jsapi_ticket
		String jspaurl = JSAPI_TICKET_URL.replace("ACCESS_TOKEN",
				access_token.split(";")[0]);
		JSONObject json = CommonUtils.httpRequest(jspaurl, "GET", null);
		String jsapi_ticket = "";
		if (json != null) {
			jsapi_ticket = json.getString("ticket") + ";" + time;
			PublishCache.jsapi_ticket.put("jsapi_ticket", jsapi_ticket);
		}

		return access_token.split(";")[0];
	}

	/**
	 * 获取Jsapi_ticket
	 * 
	 * @return
	 */
	public static String getJsapi_ticket() {
		String jsapi_ticket = PublishCache.jsapi_ticket.get("jsapi_ticket");
		Long time = System.currentTimeMillis();
		if (null == jsapi_ticket || "".equals(jsapi_ticket)
				|| "null".equals(jsapi_ticket)) {
			getAccess_token();
		} else {
			String TimeTemp = jsapi_ticket.split(";")[1];
			if (time - Long.valueOf(TimeTemp) > 7000000) {
				getAccess_token();
			}
		}
		jsapi_ticket = PublishCache.jsapi_ticket.get("jsapi_ticket");

		return jsapi_ticket.split(";")[0];
	}

	/**
	 * 获取用户openid写入Cookie
	 * 
	 * @param REDIRECT_URI
	 *            微信请求接口
	 * @param SCOPE
	 *            微信参数
	 * @param returnURL
	 *            获取后要重定向到的方法
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getOpenId(String REDIRECT_URI, String SCOPE,
			String returnURL, HttpServletRequest request,
			HttpServletResponse response) {
		String openid = "";
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if ("openid".equals(cookie.getName())
						&& !"".equals(cookie.getValue())) {
					openid = cookie.getValue();
				}
			}
		}
		if ("".equals(openid)) {
			String url = WxUtils.GETCODE_URL;// 获取code的URL
			url = url.replace("APPID", urlEnodeUTF8(XcxConstant.APPID))
					.replace("REDIRECT_URI", urlEnodeUTF8(REDIRECT_URI))
					.replace("SCOPE", SCOPE).replace("returnURL", returnURL);
			try {
				response.sendRedirect(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 根据code 发送请求 获取openID 和access_token 等信息
	 * 
	 * @param code
	 *            请求code
	 * @param request
	 * @param response
	 * @return 返回 json结果
	 */
	public static JSONObject getOpenIdAfter(String code,
			HttpServletRequest request, HttpServletResponse response) {
		// 获取openId 微信请求接口
		String url = WxUtils.getOpenIdAfter_URL;
		// 替换需要的参数
		url = url.replace("APPID", urlEnodeUTF8(XcxConstant.APPID))
				.replace("SECRET", urlEnodeUTF8(XcxConstant.APPSECRET))
				.replace("CODE", code);
		JSONObject httpRequest = httpRequest(url, "GET", null);
		return httpRequest;
	}

	/**
	 * 根据 当前用户 access_token及openId 获取用户基本信息
	 * 
	 * @param ACCESS_TOKEN
	 * @param OPENID
	 * @return
	 */
	public static JSONObject getUserInfo(String ACCESS_TOKEN, String OPENID) {
		String url = WxUtils.GETINFO_URL;
		url=url.replace("ACCESS_TOKEN", ACCESS_TOKEN).replace("OPENID", OPENID);
		return  httpRequest(url, "GET", null);
	}
	/**
	 * 
	 * 根据全局 ACCESS_TOKEN 以及 openId 查询该用户是否是粉丝  并返回粉丝的基本信息
	 * api:http://mp.weixin.qq.com/wiki/1/8a5ce6257f1d3b2afb20f83e72b72ce9.html
	 * @param OPENID	需要查询的opneId
	 * @return 返回查询后的结果 subscribe 0  不是 1 是
	 */
	public static JSONObject getFansUserInfo(String OPENID){
		String url = WxUtils.GET_MEMBER_INFO;
		url=url.replace("ACCESS_TOKEN", getAccess_token()).replace("OPENID", OPENID);
		return httpRequest(url, "GET", null);
	}

	/**
	 * 发送http 请求 并返回json 结果集
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方法
	 * @param outputStr
	 *            输出流 不需要则NULL
	 * @return
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 发送https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return 返回微信服务器响应的信息
	 */
	public static String httpsRequest(String requestUrl, String requestMethod,
			String outputStr) {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type",
					"application/x-www-form-urlencoded");
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String checkStr(String str) {
		String sl = CommonUtils.checkFull(str) == true ? "" : str;
		return sl;
	}

	public static String httpGetRequest(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			// System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String urlEnodeUTF8(String str) {
		String result = str;
		try {
			result = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
