/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package demo.action;

import java.io.IOException;
import java.util.concurrent.Callable;

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
import org.seasar.struts.config.S2ExecuteConfig;
import org.seasar.struts.util.ActionMessagesUtil;

public class JPetStoreRequestProcessor extends S2RequestProcessor {

    public static final String RETURN_URL = JPetStoreRequestProcessor.class
            .getName()
            + "_returnURL";

    @Override
    protected boolean processValidate(HttpServletRequest request,
            HttpServletResponse response, ActionForm form, ActionMapping mapping)
            throws IOException, ServletException, InvalidCancelException {

        S2ActionMapping s2ActionMapping = (S2ActionMapping) mapping;
        BeanDesc actionBeanDesc = s2ActionMapping.getActionBeanDesc();
        Class<?> actionClass = actionBeanDesc.getBeanClass();
        if (actionClass.isAnnotationPresent(Authorize.class)) {
            if (!isAuthorized()) {
                ActionMessages messages = new ActionMessages();
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "Please signin.", false));
                ActionMessagesUtil.addErrors(SingletonS2Container
                        .getComponent(HttpSession.class), messages);
                S2ExecuteConfig executeConfig = s2ActionMapping
                        .findExecuteConfig(request);
                String returnURL = s2ActionMapping.getPath() + "/"
                        + executeConfig.getMethod().getName();
                request.setAttribute(RETURN_URL, returnURL);
                ActionForward forward = s2ActionMapping
                        .createForward("/signin/signinForm");
                processForwardConfig(request, response, forward);
                return false;
            }
        }
        return super.processValidate(request, response, form, mapping);
    }

    protected boolean isAuthorized() {
        Callable<Boolean> authorizationHelper = SingletonS2Container
                .getComponent("authorizationHelper");
        try {
            return authorizationHelper.call();
        } catch (Exception e) {
            return false;
        }
    }
}
