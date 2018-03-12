package jm.frameWork.socket;
//package ms.frameWork.socket;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//import javax.websocket.EndpointConfig;
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSONObject;
//
//import ms.basic.bean.Comment;
//import ms.frameWork.util.xcx.XcxConstant;
//import ms.xcx.service.CommentService;
//
////@ServerEndpoint(value="/chat.do")
//@ServerEndpoint(value="/chat",configurator=GetHttpSessionConfigurator.class)
//@Component
//public class MsSocket {
//	
//	@Resource(name="commentService")
//	private CommentService commentService;
//	
//    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//    private static int onlineCount = 0;
//
//    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
////    private static CopyOnWriteArraySet<MsSocket> webSocketSet = new CopyOnWriteArraySet<MsSocket>();
//    
//    private static HashMap<Integer,MsSocket> memberMap;
//
//    //与某个客户端的连接会话，需要通过它来给客户端发送数据
//    private Session session;
//    
//    private Integer memberId;
//
//    /**
//     * 连接建立成功调用的方法
//     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
//     */
//    @OnOpen
//    public void onOpen(Session session,EndpointConfig config){
//    	HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
//    	System.out.println();
//    	memberId = (Integer) httpSession.getAttribute(XcxConstant.XCX_LOGIN_MEMBER_ID);
//        this.session = session;
//        addMemberMap(memberId,this);
//    }
//
//    /**
//     * 连接关闭调用的方法z
//     */
//    @OnClose
//    public void onClose(){
////        webSocketSet.remove(this);  //从set中删除
//    	removeMemberMap(memberId);
//    }
//
//    /**
//     * 收到客户端消息后调用的方法
//     * @param message 客户端发送过来的消息
//     * @param session 可选的参数
//     */
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println("来自客户端的消息:" + message);
//        JSONObject jo=new JSONObject();
//        Comment me = jo.parseObject(message, Comment.class);
//        me.setCreateTime(new Date());
//        me.setIsDel(0);
//        me.setIsRead(0);
////        commentService.addInstance(me);
//        MsSocket socket = getMsSocketObject(me.getMemberId());
//        if(socket!=null){
//        	sendMessageToOne(socket, me);
//        }
//        //群发消息
////        for(MsSocket item: webSocketSet){
////            try {
////                item.sendMessage(message);
////            } catch (IOException e) {
////                e.printStackTrace();
////                continue;
////            }
////        }
//    }
//
//    /**
//     * 发生错误时调用
//     * @param session
//     * @param error
//     */
//    @OnError
//    public void onError(Session session, Throwable error){
//        System.out.println("发生错误");
//        error.printStackTrace();
//    }
//
//    /**
//     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
//     * @param message
//     * @throws IOException
//     */
//    public void sendMessage(String message) throws IOException{
//        this.session.getBasicRemote().sendText(message);
//    }
//    
//
//    public void sendMessageToOne(MsSocket socket,Comment me) {
//    	JSONObject jo=new JSONObject();
//    	try {
//	    	jo.put("createTime", me.getCreateTime());
//	    	if(me.getDialogueType()==1){//文字消息
//	    		jo.put("dialogueType", me.getDialogueType());
//	    		jo.put("content", me.getContent());
//	    	}else{
//	    		
//	    	}
//	    	jo.put("isRead", me.getIsRead());
//			socket.sendMessage(jo.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    }
//    
//    
//    /**
//     * 获取socket连接用户对象
//     * @return
//     */
//    public static HashMap<Integer, MsSocket> getMemberMap() {
//		if (memberMap == null) {
//			synchronized (HashMap.class) {
//				if (memberMap == null) {
//					memberMap = new HashMap<Integer,MsSocket>();
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
//    public void addMemberMap(Integer id,MsSocket ms) {
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
//     * @param ms
//     */
//    public void removeMemberMap(Integer id) {
//    	HashMap<Integer,MsSocket> map = getMemberMap();
//    	synchronized(map) {
//    		map.remove(id);
//    		System.out.println("用户ID:"+id+"关闭连接！当前在线人数为" + map.size());
//    	}
//    }
//    
//    /**
//     * 添加
//     * @param id
//     * @param ms
//     */
//    public MsSocket getMsSocketObject(Integer id) {
//    	HashMap<Integer,MsSocket> map = getMemberMap();
//    	MsSocket m = map.get(id);
//		return m;
//    }
//}