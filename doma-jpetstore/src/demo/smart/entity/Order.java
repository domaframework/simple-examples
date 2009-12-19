package demo.smart.entity;

import java.io.Serializable;
import java.sql.Date;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.Table;

import demo.smart.domain.Amount;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "ORDERS_SEQ", initialValue = 1001, allocationSize = 10)
    public int orderId;

    @Column(name = "USERID")
    public String username;

    public Date orderDate;

    @Column(name = "SHIPADDR1")
    public String shipAddress1;

    @Column(name = "SHIPADDR2")
    public String shipAddress2;

    public String shipCity;

    public String shipState;

    public String shipZip;

    public String shipCountry;

    @Column(name = "BILLADDR1")
    public String billAddress1;

    @Column(name = "BILLADDR2")
    public String billAddress2;

    public String billCity;

    public String billState;

    public String billZip;

    public String billCountry;

    public String courier;

    public Amount totalPrice;

    public String billToFirstName;

    public String billToLastName;

    public String shipToFirstName;

    public String shipToLastName;

    public String creditCard;

    @Column(name = "EXPRDATE")
    public String expiryDate;

    public String cardType;

    public String locale;

    @Column(insertable = false, updatable = false)
    public String status;

}
