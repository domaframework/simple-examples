package example.common.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.seasar.doma.Association;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

@Entity(metamodel = @Metamodel)
@Table(quote = true)
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public Integer userId;
  public LocalDateTime orderDate;
  public String status;

  @Version public Integer version;

  @Association public List<OrderItem> orderItemList = new ArrayList<>();
  @Association public Payment payment;
  @Association public User user;
}
