package example.common.entity;

import org.seasar.doma.Association;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Version;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class ProductCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public Integer productId;
  public Integer categoryId;

  @Version public Integer version;

  @Association public Product product;
  @Association public Category category;
}
