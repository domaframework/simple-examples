package demo.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.ContainerConstants;
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

    public static Map<Object, Object> getRequestMap() {
        return SingletonS2Container
                .getComponent(ContainerConstants.REQUEST_SCOPE);
    }

    public static Map<Object, Object> getSessionMap() {
        return SingletonS2Container
                .getComponent(ContainerConstants.SESSION_SCOPE);
    }
}
