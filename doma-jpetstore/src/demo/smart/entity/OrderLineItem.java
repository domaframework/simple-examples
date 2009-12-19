package demo.smart.entity;

import java.io.Serializable;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import demo.smart.domain.Amount;

@Entity
@Table(name = "LINEITEM")
public class OrderLineItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public int orderId;

    @Id
    @Column(name = "LINENUM")
    public int lineNumber;

    public int quantity;

    public String itemId;

    public Amount unitPrice;

    @Column(insertable = false, updatable = false)
    public Amount listPrice;

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

    public Amount getTotal() {
        return listPrice.multiply(quantity);
    }
}
