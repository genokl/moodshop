package jm.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  页面跳转controller
 * @createDate 2016年11月15日上午10:54:16
 * @version 1.0
 */
@Controller
@RequestMapping("/jump")
public class JumpContreller {
	/**
	 * 页面跳转action
	 * @param request
	 * @param response
	 * @param pagePath
	 * @param pageName
	 * @return
	 * /jump/topage.do?pagePath=product&pageName=add
	 */
	@RequestMapping("/topage.do")
	public String topage(
			HttpServletRequest request,
			HttpServletResponse response,
			String pagePath,
			String pageName) throws Exception{
			return pagePath+"/"+pageName;
	}
	
}
