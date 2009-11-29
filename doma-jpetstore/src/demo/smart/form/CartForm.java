package demo.smart.form;

import java.util.HashMap;
import java.util.Map;

import org.seasar.struts.annotation.Required;

public class CartForm {

    @Required
    public String itemId;

    public Map<String, String> itemIds = new HashMap<String, String>();
}
