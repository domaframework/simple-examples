package demo.form;

import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.Required;

public class OrderForm {

    @Required
    @IntegerType
    public String orderId;

}
