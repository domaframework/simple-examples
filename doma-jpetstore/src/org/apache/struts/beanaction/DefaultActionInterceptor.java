package org.apache.struts.beanaction;

import java.io.Serializable;

public class DefaultActionInterceptor implements ActionInterceptor,
        Serializable {

    private static final long serialVersionUID = 1L;

    public String intercept(ActionInvoker invoker) {
        return invoker.invoke();
    }
}
