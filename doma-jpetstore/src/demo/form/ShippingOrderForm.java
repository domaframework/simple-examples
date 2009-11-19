package demo.form;

import org.seasar.struts.annotation.Required;

public class ShippingOrderForm {

    @Required
    public String shipAddress1;

    @Required
    public String shipAddress2;

    @Required
    public String shipCity;

    @Required
    public String shipState;

    @Required
    public String shipZip;

    @Required
    public String shipCountry;

    @Required
    public String shipToFirstName;

    @Required
    public String shipToLastName;

}
