package demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class LineItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public int orderId;

    @Id
    @Column(name = "LINENUM")
    public int lineNumber;

    public int quantity;

    public String itemId;

    public BigDecimal unitPrice;

    @Column(insertable = false, updatable = false)
    public BigDecimal listPrice;

    @Column(insertable = false, updatable = false)
    public String productName;

    @Column(insertable = false, updatable = false)
    public String attribute1;

    @Column(insertable = false, updatable = false)
    public String attribute2;

    @Column(insertable = false, updatable = false)
    public String attribute3;

    @Column(insertable = false, updatable = false)
    public String attribute4;

    @Column(insertable = false, updatable = false)
    public String attribute5;

    public BigDecimal getTotal() {
        if (listPrice == null) {
            return null;
        }
        return listPrice.multiply(new BigDecimal(quantity));
    }
}
