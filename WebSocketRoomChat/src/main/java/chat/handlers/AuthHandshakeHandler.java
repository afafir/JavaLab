package chat.handlers;

import chat.model.Chat;
import chat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.swing.*;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Component
@SessionAttributes(types = User.class)
public class AuthHandshakeHandler implements HandshakeHandler {
    @Autowired
    WebSocketMessagesHandler messagesHandler;

    private DefaultHandshakeHandler defaultHandshakeHandler = new DefaultHandshakeHandler();

    @Override
    public boolean doHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws HandshakeFailureException {
        ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
        User user = (User) request.getServletRequest().getSession().getAttribute("user");
        String cookie = WebUtils.getCookie(request.getServletRequest(), "X-Authorization").getValue();
        if (cookie.equals(user.getCookie() + "12312313")) {
            return defaultHandshakeHandler.doHandshake(serverHttpRequest, serverHttpResponse, webSocketHandler, map);
        } else {
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }
    }


}
