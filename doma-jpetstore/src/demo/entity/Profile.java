package demo.entity;

import java.io.Serializable;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USERID")
    public String username;

    @Column(name = "FAVCATEGORY")
    public String favouriteCategoryId;

    @Column(name = "LANGPREF")
    public String languagePreference;

    @Column(name = "MYLISTOPT")
    public boolean listOption;

    @Column(name = "BANNEROPT")
    public boolean bannerOption;

}
