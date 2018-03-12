package jm.frameWork.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
/**
 * 公共缓存类
 * @author dell
 *
 */
public class PublishCache {
	

	private static Logger log = Logger.getLogger(PublishCache.class);
	//全局access_token 一天调用2000次上限
	public static ConcurrentHashMap<String,String>  access_token = new ConcurrentHashMap<String, String>();
	public static ConcurrentHashMap<String,String>  jsapi_ticket = new ConcurrentHashMap<String, String>();
	
	/**
	 * 页面流量计算器
	 * key : 页面名称
	 * value： 页面查看数量
	 */
	public static ConcurrentHashMap<String, Integer> webCount = new ConcurrentHashMap<String, Integer>();
	/**
	 * 首页新活动
	 * key 活动ID
	 * value 活动对象
	 */
	public static List<Object> newActivity= new ArrayList<Object>();
	
	
}
