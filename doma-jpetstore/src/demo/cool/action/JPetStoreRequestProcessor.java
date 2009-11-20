package demo.cool.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.InvalidCancelException;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.action.S2RequestProcessor;
import org.seasar.struts.config.S2ActionMapping;
import org.seasar.struts.util.ActionMessagesUtil;

import demo.cool.annotation.Authorize;
import demo.cool.session.User;

public class JPetStoreRequestProcessor extends S2RequestProcessor {

    @Override
    protected boolean processValidate(HttpServletRequest request,
            HttpServletResponse response, ActionForm form, ActionMapping mapping)
            throws IOException, ServletException, InvalidCancelException {

        S2ActionMapping s2ActionMapping = (S2ActionMapping) mapping;
        BeanDesc actionBeanDesc = s2ActionMapping.getActionBeanDesc();
        Class<?> actionClass = actionBeanDesc.getBeanClass();
        if (actionClass.isAnnotationPresent(Authorize.class)) {
            User user = User.get();
            if (!user.isAuthenticated()) {
                ActionMessages messages = new ActionMessages();
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "Please signin.", false));
                ActionMessagesUtil.addErrors(SingletonS2Container
                        .getComponent(HttpSession.class), messages);
                ActionForward forward = new ActionForward("/signin/signinForm",
                        true);
                processForwardConfig(request, response, forward);
                return false;
            }
        }
        return super.processValidate(request, response, form, mapping);
    }
}
