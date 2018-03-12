//package jm.frameWork.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import com.alibaba.fastjson.JSONObject;
//
//import jm.basic.util.CommonUtils;
//import jm.basic.util.ResponseUtils;
//import jm.frameWork.util.xcx.XcxConstant;
//
///**
// * 小程序自定义session 拦截器
// * 
// * @author pzg
// * @createDate 2017年3月6日下午3:04:35
// * @type xcx
// * @version 1.0
// */
//public class XcxGetSessionInterceptor extends HandlerInterceptorAdapter {
//	
////	@Resource(name="memberServiceImpl")
////	private MemberService memberService;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//		Object lastObj = request.getParameter(XcxConstant.XCX_LAST_REQUEST_TIME);
//		Object loginMemberIdObj = request .getParameter(XcxConstant.XCX_LOGIN_MEMBER_ID);
//		Object lgonMember =request.getSession().getAttribute(XcxConstant.XCX_LOGIN_MEMBER);
//		String currentSessionId = request.getSession().getId();
//		boolean isLogin = false;
//		int statusCode = 0;
//		JSONObject jo = new JSONObject();
//		
//		if (loginMemberIdObj != null&&!CommonUtils.checkFull(loginMemberIdObj.toString())&&Integer.parseInt(loginMemberIdObj.toString()) > 0) {
//			
//			if (lastObj!=null&&StringUtils.isNumeric(lastObj.toString())&&!CommonUtils.checkFull(lastObj.toString())) {
//				Long lastRequestTime = Long.parseLong(lastObj.toString());
//				// 判断session是否过期
//				if (((lastRequestTime + (15 * 60 * 1000)) > System.currentTimeMillis())&&lgonMember!=null) {
//					isLogin = true;
//				} else {
//					statusCode=-97;
//				}
//			} else {
//				statusCode=-97;
//			}
//		} else {
//			statusCode=-99;
//		}
//
//		if(statusCode==-97){
////			Member loginMember =memberService.getInstanceByID(Integer.parseInt(loginMemberIdObj.toString()));
//			//session 设置登录对象
////			request.getSession().setAttribute(XcxConstant.XCX_LOGIN_MEMBER, loginMember);
////			request.getSession().setAttribute(XcxConstant.XCX_LOGIN_MEMBER_ID, loginMember.getId());
//			isLogin=true;
//		}
//		
//		
////		if (isLogin == false) {
////			jo.put("statusCode", statusCode);	
////			jo.put("currentSessionId", currentSessionId);
////			jo.put("lastRequestTime", System.currentTimeMillis());
////			ResponseUtils.xcxRenderJSON(response, request, jo);
////		} else {
////			return true;
////		}
//		return true;
//	}
//
//}
