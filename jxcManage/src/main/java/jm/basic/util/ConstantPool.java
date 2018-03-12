package jm.basic.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量池
 * 
 * @author pzg
 *
 */
public class ConstantPool {
	// 0测试  1生产环境
	public static Integer SERVER_STATUS = 0;
	//项目域名
	public static String DOMAIN = null;
	//超级管理员openId
	public static String SUPER_ADMIN_OPENID = null;
	
	/**
	 * 请求状态标识
	 */
	//无状态
	public static final Integer STATUS_CODE_INIT=0;
	//成功
	public static final Integer STATUS_CODE_SUCCESS=1;
	//传入参数错误
	public static final Integer STATUS_CODE_PARAMETER_ERROR=-1;
	//权限不足
	public static final Integer STATUS_CODE_AUTHORITY_ERROR=-2;
	//需要重新登录
	public static final Integer STATUS_CODE_LOGIN=-3;
	//发生异常
	public static final Integer STATUS_CODE_EXCEPTION=-7;
	
	/**
	 * 消息类型
	 */
	public static final String MESSAGE_TYPE_KEY="messageType";
	//个人对个人
	public static final Integer MESSAGE_P2P=0;
	//系统消息
	public static final Integer MESSAGE_SYSTEM=1;
	//连接打开
	public static final Integer MESSAGE_CONNECTED_OPEN=3;
	//连接关闭
	public static final Integer MESSAGE_CONNECTED_CLOSE=4;
	//时间调整消息
	public static final Integer MESSAGE_TIME_CHANGE=5;
	
	/**
	 * 价格类型
	 * 0白天1夜间
	 * 定时任务切换
	 */
	public static final Integer PRICE_TYPE=0;
	

//	public final static String SYSTEM_DOMAIN_NAME = "47.92.156.78";
	public final static String SYSTEM_DOMAIN_NAME = "127.0.0.1:8080";
	
	/**
	 * 项目域名
	 */
	public final static String  SYSTEM_TEST_DOMAIN_NAME = "http://asd.s1.natapp.cc";
	
	/**
	 * 系统登录页面
	 */
	public final static String PAGE_LOGIN = "jump/topage.do?pagePath=page&pageName=login";
	/**
	 * 系统主业
	 */
	public final static String PAGE_SYSTEM_INDEX = "jump/topage.do?pagePath=page/console&pageName=index";
	
	
	
	
	public final static String CONNECT_SESSION_ID = "connectsessionId";
	/**
	 * 当前登陆者标示
	 */
	public final static String LOGIN_MEMBER = "loginMember";
	/**
	 * 是否是管理员
	 */
	//public final static String IS_ADMIN = "isAdmin";
	public final static String IS_TEST_ADMIN_END="isTestAdminEnd";
	/**
	 * CRM 接口请求连接
	 */
	public static String CRM_URL =null;

	public final static int pageSize = 10;
	
	
	
	public static String getDOMAIN() {
		return DOMAIN;
	}
	public static void setDOMAIN(String dOMAIN) {
		DOMAIN = dOMAIN;
	}





	
	
}
