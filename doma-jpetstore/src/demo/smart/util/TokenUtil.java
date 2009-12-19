package demo.smart.util;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.TokenProcessor;


public class TokenUtil {

    public static void save() {
        TokenProcessor.getInstance()
                .saveToken(ExternalContextUtil.getRequest());
    }

    public static boolean isValid() {
        return TokenProcessor.getInstance().isTokenValid(
                ExternalContextUtil.getRequest(), true);
    }

    public static ActionMessages validate() {
        ActionMessages errors = new ActionMessages();
        if (!isValid()) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "errors.invalid", "Token"));
        }
        return errors;
    }
}
