package com.vehicle_alert.configuration;

import com.vehicle_alert.repository.WebSocketSessionRepository;
import com.vehicle_alert.interfaces.WebsocketService;
import com.vehicle_alert.utility.SocketTextHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
    WebsocketService websocketService;

    WebSocketSessionRepository webSocketSessionRepository;

    public WebSocketConfiguration(WebsocketService websocketService, WebSocketSessionRepository webSocketSessionRepository) {
        this.websocketService = websocketService;
        this.webSocketSessionRepository = webSocketSessionRepository;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketTextHandler(websocketService, webSocketSessionRepository), "/android/live");
    }
}
