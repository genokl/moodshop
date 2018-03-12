package jm.frameWork.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jm.basic.util.CommonUtils;
/**
 * 全局异常处理器
 * @author pzg
 * @createDate 2016年11月15日上午10:59:22
 * @version 1.0
 */
public class GlobalException implements HandlerExceptionResolver{
	
	private Logger log = Logger.getLogger(GlobalException.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception exception) {
		log.error(CommonUtils.exception2Str(exception));
		ModelAndView model = new ModelAndView();
		model.setViewName("../404");
		return model;
	}

}
