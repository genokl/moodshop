package jm.frameWork.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import jm.basic.util.ConstantPool;
import jm.frameWork.cache.PublishCache;
import jm.frameWork.util.xcx.XcxServer;

/**
 * 初始化服务
 * @author pzg
 *
 */
public class AppServer extends HttpServlet {
	
	@Override
	public void init(){
		InputStream in =null;
		Properties pro = null;
		
			try {
				in = new BufferedInputStream(new FileInputStream(
						Thread.currentThread().getContextClassLoader().getResource("/").getPath() + "wx.properties"));
				pro = new Properties();
				pro.load(in);
				in.close();
				String appStatus = pro.getProperty("app.server.is.test");
				if (System.getProperties().get("os.name").toString().toLowerCase().contains("window")) {
					ConstantPool.setDOMAIN(ConstantPool.SYSTEM_TEST_DOMAIN_NAME);
					System.out.println(ConstantPool.DOMAIN);
					System.out.println("当前为测试环境无需加载微信参数！");
					System.out.println("生成假参数...........");
					PublishCache.access_token.put("access_token", "access_token;" + System.currentTimeMillis());
				} else {
					ConstantPool.setDOMAIN(ConstantPool.SYSTEM_DOMAIN_NAME);
					System.out.println("生产环境微信参数加载中................");
					WxUtils.getAccess_token();
				}
				//初始化小程序 
				XcxServer.initXcxServer(appStatus);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}