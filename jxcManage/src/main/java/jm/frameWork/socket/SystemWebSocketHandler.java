//package jm.frameWork.socket;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//import java.util.UUID;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//import com.alibaba.fastjson.JSONObject;
////import com.sun.tools.javac.resources.legacy;
//
//import jm.basic.bean.Comment;
//import jm.basic.util.CommonUtils;
//import jm.basic.util.ConstantPool;
//import jm.frameWork.util.xcx.XcxConstant;
//import jm.service.CommentService;
//import jm.service.MemberService;
//
//public class SystemWebSocketHandler implements WebSocketHandler {
//	private static final Log logger;
//	 
////    private static final ArrayList<WebSocketSession> users;
//	
//	@Resource(name="commentService")
//	private CommentService commentService;
//	
//	private static HashMap<String,WebSocketSession> memberMap;
//	
//	private static ArrayList<WebSocketSession> memberList;
// 
//    static {
//    	memberMap = new HashMap<String,WebSocketSession>();
//    	memberList = new ArrayList<WebSocketSession>();
//        logger = LogFactory.getLog(SystemWebSocketHandler.class);
//    }
//    /**
//     * 连接关闭
//     */
//	@Override
//	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1)throws Exception {
//		String userName = (String) session.getAttributes().get(XcxConstant.XCX_LOGIN_MEMBER_ID);
//		logger.debug("websocket connection closed......");
//		
//		removeMemberMap(userName,session);
//	}
//
//	/**
//	 * 连接打开
//	 */
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session)throws Exception {
////		String loginmemberId =(String) session.getAttributes().get(XcxConstant.XCX_LOGIN_MEMBER_ID);
//		String uuid=UUID.randomUUID().toString();
//    	logger.debug("user:"+uuid+" connect to the websocket success......");
//    	addMemberMap(uuid, session);
//        //查询未读消息
//    	JSONObject jo=new JSONObject();
//    	jo.put("connectsessionId", uuid);
//    	jo.put(ConstantPool.MESSAGE_TYPE_KEY, ConstantPool.MESSAGE_CONNECTED_OPEN);
//    	TextMessage message=new TextMessage(jo.toString());
//    	sendMessageToUser(uuid, message);
//	}
//	
//	/**
//	 * 收到消息
//	 */
//	@Override
//	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
//			throws Exception {
//		String loginmemberId =(String) session.getAttributes().get(XcxConstant.XCX_LOGIN_MEMBER_ID);
//		JSONObject jo=new JSONObject().parseObject((String) message.getPayload());
//		//获取连接session标识
//		Integer messageType = jo.getInteger(ConstantPool.MESSAGE_TYPE_KEY);
//		String connectsessionId = jo.getString(ConstantPool.CONNECT_SESSION_ID);
//		/**
//		 * 打开连接
//		 */
//		if(messageType==3){
//			addMemberMap(connectsessionId, session);
//		}else
//			/**
//			 * 关闭连接
//			 */
//			if(messageType==4){
//			removeMemberMap(connectsessionId,null);
//		}else if(messageType==0)
//			/**
//			 * 获取对话消息
//			 */
//			if(messageType==0){
//			String mess= (String)message.getPayload();
//			Comment comment = jo.parseObject(mess, Comment.class);
//			jo.put(ConstantPool.MESSAGE_TYPE_KEY,ConstantPool.MESSAGE_P2P);
//			TextMessage a=new TextMessage((CharSequence) message.getPayload());
//			comment.setCreateTime(new Date());
//			comment.setIsDel(0);
//			sendMessageToUser(connectsessionId, a);
//			commentService.addInstance(comment);
//		}
//	}
//
//	@Override
//	public void handleTransportError(WebSocketSession session, Throwable arg1)
//			throws Exception { 
//		try {
//			if(session.isOpen()){
//	            session.close();
//	        }
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			removeConnectByError(session);
//		}
////		removeConnectByError(session);
//        logger.error("websocket connection  error and closed......",arg1);
////        String loginmemberId =(String) session.getAttributes().get(XcxConstant.XCX_LOGIN_MEMBER_ID);
////        removeMemberMap(loginmemberId);
//	}
//
//	@Override
//	public boolean supportsPartialMessages() {
//		return false;
//	}
//	/**
//     * 给所有在线用户发送消息
//     *
//     * @param message
//     */
//    public void sendMessageToUsers(WebSocketMessage<?> message) {
//    	String memberId="-1";
//    	HashMap<String,WebSocketSession> map = getMemberMap();
//    	Iterator<Entry<String,WebSocketSession>> iter = map.entrySet().iterator();
//    	while (iter.hasNext()) {
//    		try {
//	    		Map.Entry e = iter.next();
//	    		memberId = (String) e.getKey();
//	    		WebSocketSession user = (WebSocketSession) e.getValue();
//	    		if(user.isOpen()){
//	    			user.sendMessage(message);
//	    		}
//    		} catch (Exception e) {
//              logger.error("send message to "+memberId+" error!",e);
//        	}
//		}
//    }
// 
//    /**
//     * 给某个用户发送消息
//     *
//     * @param userName
//     * @param message
//     */
//    public void sendMessageToUser(String memberId, TextMessage message) {
//    	HashMap<String,WebSocketSession> map = getMemberMap();
//    	WebSocketSession user = map.get(memberId);
//		if(user!=null){
//			try {
//	    		if(user.isOpen()){
//	    			user.sendMessage(message);
//	    		}
//			} catch (Exception e) {
//	          logger.error("send message to "+memberId+" error!",e);
//	    	}
//		}
//    }
//    
//    /**
//     * 获取socket连接用户对象
//     * @return
//     */
//    public static HashMap<String, WebSocketSession> getMemberMap() {
//		if (memberMap == null) {
//			synchronized (HashMap.class) {
//				if (memberMap == null) {
//					memberMap = new HashMap<String,WebSocketSession>();
//				}
//			}
//		}
//		return memberMap; 
//	}
//    /**
//     * 添加
//     * @param id
//     * @param ms
//     */
//    public void addMemberMap(String id,WebSocketSession ms) {
//    	HashMap map = getMemberMap();
//    	synchronized (map) {
//    		map.put(id, ms);
//        	System.out.println("用户ID:"+id+"建立连接！当前在线人数为" + map.size());
//		}
//    }
//    
//    /**
//     * 添加
//     * @param id
//     * @param session 
//     * @param ms
//     */
//    public void removeMemberMap(String id, WebSocketSession session) {
//    	HashMap<String,WebSocketSession> map = getMemberMap();
//    	synchronized(map) {
//    		if(CommonUtils.checkFull(id)){//id为空
//    			removeConnectByError(session);
//    		}else{
//    			map.remove(id);
//    			System.out.println("用户ID:"+id+"关闭连接！当前在线人数为" + map.size());
//    		}
//    	}
//    }
//    
//    /**
//     * 添加
//     * @param id
//     * @param ms
//     */
//    public WebSocketSession getMsSocketObject(String id) {
//    	HashMap<String,WebSocketSession> map = getMemberMap();
//    	WebSocketSession m = map.get(id);
//		return m;
//    }
//    
//    public void removeConnectByError(WebSocketSession wss) {
//    	HashMap<String,WebSocketSession> map = getMemberMap();
//    	Set<Entry<String,WebSocketSession>> set = map.entrySet();
//    	String key="";
//    	for (Entry<String, WebSocketSession> entry : set) {
//    		WebSocketSession value = entry.getValue();
//			if(value.equals(wss)){
//				key=entry.getKey();
//			}
//		}
//    	if(!CommonUtils.checkFull(key)){
//    		removeMemberMap(key,null);
//    	}
//    }
//    
//    
//}
