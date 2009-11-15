package demo.entity;

import java.io.Serializable;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String username;

    public String password;

    public String email;

    public String firstName;

    public String lastName;

    public String status;

    public String address1;

    public String address2;

    public String city;

    public String state;

    public String zip;

    public String country;

    public String phone;

    public String favouriteCategoryId;

    public String languagePreference;

    public boolean listOption;

    public boolean bannerOption;

    public String bannerName;

}
