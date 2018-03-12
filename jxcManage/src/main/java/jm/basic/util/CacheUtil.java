package jm.basic.util;
//package cn.babasport.common.util;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import cn.frameWork.util.LoadEntityDB;
//import cn.zgyt.basic.bean.Article;
//
//public class CacheUtil {
//
//	/**
//	 * 首页文章Map
//	 *  key Article ID 
//	 * value Article对象
//	 * 
//	 */
//	public static List<Article> indexNewsList= new ArrayList<Article>();
//	
//	/**
//	 * 首页文章Map
//	 *  key Article ID 
//	 * value Article对象
//	 */
//	public static List<Article> indexProductsList= new ArrayList<Article>();
//
//	/**
//	 * 首页文章Map
//	 *  key Article ID 
//	 * value Article对象
//	 */
//	public static List<Article> indexTechList= new ArrayList<Article>();
//	
//	/**
//	 * 首页文章Map
//	 *  key Article ID 
//	 * value Article对象
//	 */
//	public static Map<Integer ,Article> indexRegularDataMap= new ConcurrentHashMap<Integer, Article>();
//	
//	/**
//	 * 关键词Map
//	 *  key Article ID 
//	 * value Article对象
//	 */
//	public static List<Article> keyWordsList= new ArrayList<Article>();
//	
//	
//	
//	/**
//	 * 加载首页数据
//	 */
//	public static void loadIndexData() {
//		LoadEntityDB db = new LoadEntityDB();
//	
//		/**
//		 *  加载首页公司要闻
//		 */
//		CacheUtil.indexNewsList = db.getNewListDataFromDB();//加载公司要闻列表
//		/**
//		 *  加载首页产品列表
//		 */
//		CacheUtil.indexProductsList = db.getProductListDataFromDB();
//		
//		/**
//		 * indexProductsMap
//		 * 此map中
//		 * key ArticleType  1，2，6 		1，公司概况  2,联系我们  6，人力资源
//		 * value Article对象
//		 */
//		List<Article> l4 = new LoadEntityDB().getRegularListDataFromDB();
//		if (l4!=null) {
//			//加载固定数据列表 分别是  公司概况，联系我们，人力资源
//			for(int i=0;i<l4.size();i++){
//				CacheUtil.indexRegularDataMap.put(l4.get(i).getArticleType(), l4.get(i));
//			}
//		}
//		
//		CacheUtil.indexTechList=db.getTechListDataFromDB();
//		
//	}
//	
//	/**
//	 * 缓存文章关键词集合
//	 */
//	public static void loadKeyWordData() {
//		CacheUtil.keyWordsList = new LoadEntityDB().getKeyWordsDataFromDB();//加载关键词列表
//	}
//}
