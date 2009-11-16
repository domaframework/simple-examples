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

    @Column(name = "PRODUCTID")
    public String productId;

    @Column(insertable = false, updatable = false)
    public String productName;

    @Column(insertable = false, updatable = false)
    public String productCategoryId;

    @Column(insertable = false, updatable = false)
    public String productDescription;

    public BigDecimal listPrice;

    public BigDecimal unitCost;

    @Column(name = "SUPPLIER")
    public int supplierId;

    public String status;

    @Column(name = "ATTR1")
    public String attribute1;

    @Column(name = "ATTR2")
    public String attribute2;

    @Column(name = "ATTR3")
    public String attribute3;

    @Column(name = "ATTR4")
    public String attribute4;

    @Column(name = "ATTR5")
    public String attribute5;

    @Column(name = "QTY", insertable = false, updatable = false)
    public Integer quantity;

}
