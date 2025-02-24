package example.common.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.seasar.doma.Association;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Version;

@Entity(metamodel = @Metamodel)
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public String name;
  public BigDecimal price;
  public Integer stockQuantity;
  public LocalDateTime createdAt;

  @Version public Integer version;

  @Association public List<Category> categoryList = new ArrayList<>();
}
