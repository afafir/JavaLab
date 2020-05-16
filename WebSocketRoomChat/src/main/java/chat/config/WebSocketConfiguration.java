package chat.config;

import chat.handlers.AuthHandshakeHandler;
import chat.handlers.WebSocketMessagesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Autowired
    private WebSocketMessagesHandler handler;

    @Autowired
    private AuthHandshakeHandler handshakeHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handler, "/message")
                .setHandshakeHandler(handshakeHandler)
                .withSockJS()
                .setClientLibraryUrl("https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js");
    }
}
