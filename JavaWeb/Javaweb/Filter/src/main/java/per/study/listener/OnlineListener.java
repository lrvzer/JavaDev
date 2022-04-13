package per.study.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session销毁
 *  1.手动销毁
 *      se.getSession().invalidate();
 *  2.自动销毁
 *      <session-config>
 *         <session-timeout>1</session-timeout>
 *     </session-config>
 */
public class OnlineListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
        if (onlineCount == null) {
            onlineCount = new Integer(1);
        } else {
            int count = onlineCount.intValue();
            onlineCount = new Integer(count + 1);
        }
        ctx.setAttribute("OnlineCount", onlineCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
