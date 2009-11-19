package demo.entity;

import java.io.Serializable;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Signon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String username;

    public String password;

}
