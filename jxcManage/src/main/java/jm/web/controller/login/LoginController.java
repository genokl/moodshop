package jm.web.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import jm.basic.bean.User;
import jm.basic.util.CommonUtils;
import jm.basic.util.ResponseUtils;
import jm.frameWork.cache.cacheBeans.UserCacheInfo;
import jm.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Resource(name="userService")
	private UserService userService;
	
	private Logger log = Logger.getLogger(LoginController.class);
	
	/**
	 * 登录controller
	 */
	@RequestMapping("/userLogin.do")
	public void login(
			HttpServletRequest request,
			HttpServletResponse response,
			UserCacheInfo u,
			String code,
			String requestKey,
			String encryptedData,
			String iv) throws Exception{
		User loginUser = u.getUser();
		log.info("收到【"+CommonUtils.getIpAddr(request)+"】登录请求！！！ ");
		JSONObject rjo =new JSONObject();
		int statusCode = 0;
		try {
			boolean isSessionLogin= false,
					isLoginTrue=false;
//			System.out.println(XcxUtil.testRequestKey(request, requestKey,u.getLastRequestTime()));
			loginUser=userService.getInstanceByID(1);
			System.out.println(loginUser.getPersonnelNumber());
		} catch (Exception e) {
			log.info("来自【"+CommonUtils.getIpAddr(request)+"】登录失败！！反生异常  !code:"+code
					+" ;encryptedData :  "+encryptedData+"  ; iv: "+iv +" ; ex: " +e.getMessage());
			statusCode=-1;
			e.printStackTrace();
		}
		rjo.put("statusCode", statusCode);
		ResponseUtils.xcxRenderJSON(response,request, rjo);
	}
	
	
}
