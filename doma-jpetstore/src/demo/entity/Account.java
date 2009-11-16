package demo.entity;

import java.io.Serializable;

import org.seasar.doma.Column;
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

    @Column(name = "ADDR1")
    public String address1;

    @Column(name = "ADDR2")
    public String address2;

    public String city;

    public String state;

    public String zip;

    public String country;

    public String phone;

    @Column(name = "FAVCATEGORY")
    public String favouriteCategoryId;

    @Column(name = "LANGPREF")
    public String languagePreference;

    @Column(name = "MYLISTOPT")
    public boolean listOption;

    @Column(name = "BANNEROPT")
    public boolean bannerOption;

    public String bannerName;

}
