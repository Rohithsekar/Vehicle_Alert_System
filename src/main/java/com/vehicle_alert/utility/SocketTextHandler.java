package com.vehicle_alert.utility;



import com.sun.istack.NotNull;
import com.vehicle_alert.entity.WebSocketSessionInfo;
import com.vehicle_alert.repository.WebSocketSessionRepository;
import com.vehicle_alert.interfaces.WebsocketService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.Instant;
import java.util.Objects;

@Component
@NoArgsConstructor
public class SocketTextHandler extends TextWebSocketHandler {

    WebsocketService websocketService;

    WebSocketSessionRepository webSocketSessionRepository;

    public SocketTextHandler(WebsocketService websocketService,
                             WebSocketSessionRepository webSocketSessionRepository) {
        this.websocketService = websocketService;
        this.webSocketSessionRepository = webSocketSessionRepository;
    }

    @Override
    protected void handleTextMessage(@NotNull WebSocketSession session, @NotNull TextMessage message) throws Exception {

        websocketService.processPayload(session, message);

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session)  {

        // Create WebSocketSessionEntity instance
        WebSocketSessionInfo webSocketSessionEntity = createWebSocketSessionInfo(session);

        // Save the instance to the database
        webSocketSessionRepository.save(webSocketSessionEntity);
    }

    private WebSocketSessionInfo createWebSocketSessionInfo(WebSocketSession session) {

        WebSocketSessionInfo sessionInfo = new WebSocketSessionInfo();
        sessionInfo.setSessionId(session.getId());
        sessionInfo.setLocalAddress(Objects.requireNonNull(session.getLocalAddress()).toString());
        sessionInfo.setConnectedTime(Instant.now().getEpochSecond());
        return sessionInfo;
    }
}

