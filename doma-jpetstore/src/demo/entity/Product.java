package demo.entity;

import java.io.Serializable;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String productId;

    public String categoryId;

    public String name;

    public String description;

}
