//package jm.frameWork.socket;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//@Configuration
//@EnableWebMvc
//@EnableWebSocket
//public class WebSocketConfig extends WebMvcConfigurerAdapter implements
//		WebSocketConfigurer {
//
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(systemWebSocketHandler(),"/websocket.do").addInterceptors(new WebSocketHandshakeInterceptor()).setAllowedOrigins("*");
//		 
//        registry.addHandler(systemWebSocketHandler(), "/sockjs/chat.do").addInterceptors(new WebSocketHandshakeInterceptor())
//                .withSockJS();
//	}
//	@Bean
//    public WebSocketHandler systemWebSocketHandler(){
//        return new SystemWebSocketHandler();
//    }
//}
