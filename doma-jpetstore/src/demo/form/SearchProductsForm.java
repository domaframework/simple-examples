package demo.form;

import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

public class SearchProductsForm {

    @Required(msg = @Msg(key = "Please enter a keyword to search for, then press the search button.", resource = false))
    public String keyword;

}
