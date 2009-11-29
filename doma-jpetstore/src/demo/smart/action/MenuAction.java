package demo.smart.action;

import org.seasar.struts.annotation.Execute;

public class MenuAction {

    @Execute(validator = false)
    public String index() {
        return "index.jsp";
    }
}
