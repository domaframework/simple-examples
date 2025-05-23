package example.common.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Association;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Version;

@Entity
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public Integer userId;
  public Integer productId;
  public Integer rating;
  public String comment;
  public LocalDateTime createdAt;

  @Version public Integer version;

  @Association public User user;
  @Association public Product product;
}
