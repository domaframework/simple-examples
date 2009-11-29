package demo.smart.form;

import org.seasar.struts.annotation.Required;

public class NewAccountForm {

    @Required
    public String username;

    @Required
    public String password;

    @Required
    public String repeatedPassword;

    @Required
    public String email;

    @Required
    public String firstName;

    @Required
    public String lastName;

    @Required
    public String address1;

    @Required
    public String address2;

    @Required
    public String city;

    @Required
    public String state;

    @Required
    public String zip;

    @Required
    public String country;

    @Required
    public String phone;

    @Required
    public String favouriteCategoryId;

    @Required
    public String languagePreference;

    @Required
    public boolean listOption;

    @Required
    public boolean bannerOption;

}
