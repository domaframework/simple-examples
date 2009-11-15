package demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String itemId;

    public String productId;

    @Column(insertable = false, updatable = false)
    public String productName;

    @Column(insertable = false, updatable = false)
    public String productCategoryId;

    @Column(insertable = false, updatable = false)
    public String productDescription;

    public BigDecimal listPrice;

    public BigDecimal unitCost;

    public int supplierId;

    public String status;

    public String attribute1;

    public String attribute2;

    public String attribute3;

    public String attribute4;

    public String attribute5;

    public int quantity;

}
