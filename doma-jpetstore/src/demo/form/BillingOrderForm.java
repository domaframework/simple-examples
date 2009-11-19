package demo.form;

import org.seasar.struts.annotation.Required;

public class BillingOrderForm {

    @Required
    public String billAddress1;

    @Required
    public String billAddress2;

    @Required
    public String billCity;

    @Required
    public String billState;

    @Required
    public String billZip;

    @Required
    public String billCountry;

    @Required
    public String billToFirstName;

    @Required
    public String billToLastName;

    @Required
    public String creditCard;

    @Required
    public String expiryDate;

    @Required
    public String cardType;

    public boolean shippingAddressRequired;

}
