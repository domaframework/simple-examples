package example.common.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(metamodel = @Metamodel, naming = NamingType.SNAKE_LOWER_CASE)
@Table(quote = true)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public String name;
  public String email;

  public LocalDateTime createdAt;

  @Version public Integer version;

  @Association public List<Role> roleList = new ArrayList<>();
}
