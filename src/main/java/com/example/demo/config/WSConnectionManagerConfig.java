package com.example.demo.config;

import com.example.demo.service.StompSessionHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Configuration
public class WSConnectionManagerConfig {

    @Autowired
    private SimpleWsHandler simpleWsHandler;

    @Autowired
    private StompSessionHandlerService stompSessionHandlerService;

    @Bean
    public WebSocketConnectionManager wsConnectionManager() {

        StandardWebSocketClient standardWebSocketClient = new StandardWebSocketClient();

        //Generates a web socket connection
        String webSocketUri = "ws://localhost:9441/ws/random";
        WebSocketConnectionManager manager = new WebSocketConnectionManager(
                standardWebSocketClient,
                simpleWsHandler, //Must be defined to handle messages
                webSocketUri);

        //Will connect as soon as possible
        manager.setAutoStartup(false);

        return manager;
    }

    @Bean
    public WebSocketStompClient webSocketStompClient() {

        String webSocketUri = "ws://localhost:9441/ws/random1";

        StandardWebSocketClient standardWebSocketClient = new StandardWebSocketClient();
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(standardWebSocketClient);
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());

        webSocketStompClient.connect(webSocketUri, stompSessionHandlerService);

        webSocketStompClient.setAutoStartup(true);

        webSocketStompClient.start();

        return webSocketStompClient;
    }
}
