package demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public int orderId;

    public String username;

    public Date orderDate;

    public String shipAddress1;

    public String shipAddress2;

    public String shipCity;

    public String shipState;

    public String shipZip;

    public String shipCountry;

    public String billAddress1;

    public String billAddress2;

    public String billCity;

    public String billState;

    public String billZip;

    public String billCountry;

    public String courier;

    public BigDecimal totalPrice;

    public String billToFirstName;

    public String billToLastName;

    public String shipToFirstName;

    public String shipToLastName;

    public String creditCard;

    public String expiryDate;

    public String cardType;

    public String locale;

    public String status;

}
