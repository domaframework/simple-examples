package demo.smart.entity;

import java.io.Serializable;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CATID")
    public String categoryId;

    public String name;

    @Column(name = "DESCN")
    public String description;

}
