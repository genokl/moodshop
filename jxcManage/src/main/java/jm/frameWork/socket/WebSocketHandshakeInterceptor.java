//package jm.frameWork.socket;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import jm.frameWork.util.xcx.XcxConstant;
//
//public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
//	 
//    private static Log logger = LogFactory.getLog(HandshakeInterceptor.class);
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object
//                > attributes) throws Exception {
////    	logger.debug("beforeHandshake start.....");
////    	logger.debug(request.getClass().getName());
////        if (request instanceof ServletServerHttpRequest) {
////            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
////            HttpSession session = servletRequest.getServletRequest().getSession(false);
////            if (session != null) {
////                //使用userName区分WebSocketHandler，以便定向发送消息
////                String userName =session.getAttribute(XcxConstant.XCX_LOGIN_MEMBER_ID)+"";
////                logger.info(userName+" login");
////                attributes.put(XcxConstant.XCX_LOGIN_MEMBER_ID,userName);
////            }else{
////            	logger.debug("httpsession is null");
////            }
////        }
//        return true;
//    }
// 
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
//    }
//}