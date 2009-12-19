package demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.SingletonS2Container;

public final class ExternalContextUtil {

    public static void invalidateSession() {
        HttpSession session = getSession();
        session.invalidate();
    }

    public static HttpServletRequest getRequest() {
        return SingletonS2Container.getComponent(HttpServletRequest.class);
    }

    public static HttpSession getSession() {
        return SingletonS2Container.getComponent(HttpSession.class);
    }

}
