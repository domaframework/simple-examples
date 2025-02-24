package example.common.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.seasar.doma.Association;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Version;

@Entity
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public Integer orderId;
  public BigDecimal amount;
  public LocalDateTime paymentDate;

  @Version public Integer version;

  @Association public Order order;
}
