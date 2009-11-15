package demo.util;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.seasar.framework.container.ContainerConstants;
import org.seasar.framework.container.SingletonS2Container;

public final class ExternalContextUtil {

    public static void invalidateSession() {
        HttpSession session = SingletonS2Container
                .getComponent(ContainerConstants.SESSION_NAME);
        session.invalidate();
    }

    public static Map<Object, Object> getParamMap() {
        return SingletonS2Container.getComponent(ContainerConstants.PARAM);
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
