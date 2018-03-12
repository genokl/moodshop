//package jm.frameWork.interceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.log4j.Logger;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import jm.basic.bean.Member;
//import jm.basic.util.CommonUtils;
//import jm.basic.util.ConstantPool;
//import jm.xcx.service.MemberLoginLogService;
//import jm.xcx.service.MemberService;
///**
// * 获取openId 过滤器
// * @author wxy
// * @createDate 2017年11月20日 11:28:54
// * @version 1.0
// */
//public class GetOpenIdInterceptor implements HandlerInterceptor {
//	
//	private Logger log = Logger.getLogger(GetOpenIdInterceptor.class);
//	
//	@Resource(name="memberServiceImpl")
//	private MemberService memberService;		//会员对象
//	@Resource(name="memberLoginLogServiceImpl")
//	private MemberLoginLogService memberLoginLogService;		//会员登录对象
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//		// 获取 跳转页面
//		String returnURL = request.getRequestURI();
//		String query = request.getQueryString();
//		if (null == returnURL) {
//			returnURL = "";
//		}else{
//			//封装回调跳转地址
//			returnURL+=((query==null)?"":"?"+query);//CommonUtils.strToBase64(query));
//		}
//		if(returnURL.contains("ifpsWxController")||returnURL.contains("test")){
//			return true;
//		}
//		request.getSession().setAttribute("rURL", returnURL);
//		log.info("拦截到 来自【" + CommonUtils.getIpAddr(request) + "】访问请求！跳转地址："+ returnURL);
//		HttpSession session=request.getSession();
//		Object loginObject = session.getAttribute(ConstantPool.LOGIN_MEMBER);
//		Member loginMember = null;
//		if(loginObject==null){
//		}else{
//			loginMember=(Member) loginObject;
//		}
////		if(loginMember==null){
////			String openid = getCookieOpenId(request);
////			if (CommonUtils.checkFull(openid)) {
////				WxUtils.requestWx(WxConstantsPool.DOMAIN + request.getServletContext().getContextPath() + 
////						"/ifpsWxController/findCode.action", "snsapi_userinfo","1", request, response);
////				return false;
////			}else{
////				MemberQuery me= new MemberQuery();
////				me.createCriteria().andOpenidEqualTo(openid);
////				List<Member> ml = memberService.selectMemberByExample(me);
////				if(ml!=null&&ml.size()>=1){
////					request.getSession().setAttribute(ConstantPool.LOGIN_MEMBER, ml.get(0));
////					//添加用户登录日志
////					 MemberLoginLog loginLog =
////							 CommonUtils.getUserLoginLogByUserAgent(request, ml.get(0).getId());
////					 memberLoginLogService.saveLoginLog(loginLog);
////					 return true;
////				}else{
////					WxUtils.requestWx(WxConstantsPool.DOMAIN + request.getServletContext().getContextPath() + 
////							"/ifpsWxController/findCode.action", "snsapi_userinfo","1", request, response);
////					return false;
////				}
////			}
////			
////		}
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
//	}
//	
//	/**
//	 * 从cookie 中获取openId 
//	 * @param request
//	 * @return 没有则返回空
//	 */
//	private String getCookieOpenId(HttpServletRequest request) {
//		Cookie[] cookies = request.getCookies();
//		if (null != cookies) {
//			for (Cookie cookie : cookies) {
//				if ("openid".equals(cookie.getName())&& !"".equals(cookie.getValue())) {
//					return cookie.getValue();
//				}
//			}
//		}
//		return null;
//	}
//}
