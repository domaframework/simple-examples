package example.common.entity;

import java.math.BigDecimal;
import org.seasar.doma.Association;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

@Entity(metamodel = @Metamodel)
@Table(name = "order_item")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public Integer orderId;
  public Integer productId;
  public Integer quantity;
  public BigDecimal price;

  @Version public Integer version;

  @Association public Order order;
  @Association public Product product;
}
