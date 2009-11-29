package demo.smart.action;

import org.seasar.struts.annotation.Execute;

public class IndexAction {

    @Execute(validator = false)
    public String index() {
        return "/menu?redirect=true";
    }
}
