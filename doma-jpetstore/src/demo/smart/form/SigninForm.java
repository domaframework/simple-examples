package demo.smart.form;

import org.seasar.struts.annotation.Required;

public class SigninForm {

    @Required
    public String username;

    @Required
    public String password;

    public String returnURL;

}
