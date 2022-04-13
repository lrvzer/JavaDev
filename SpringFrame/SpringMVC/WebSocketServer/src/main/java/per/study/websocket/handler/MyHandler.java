package per.study.websocket.handler;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyHandler implements WebSocketHandler
{
    Logger log = LoggerFactory.getLogger(MyHandler.class);

    /*用来保存连接进来的session*/
    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    /**
     * 三次握手成功
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.debug("用户{}连接成功", session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.debug("{}回话出现错误：{}", session, exception);
        if (session.isOpen()) {
            session.close();
        }
    }

    /**
     * 关闭连接时调用
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessions.remove(session);
        log.debug("{}连接已经断开，现从list中删除，状态信息为{}", session, closeStatus);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
