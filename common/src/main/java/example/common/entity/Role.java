package example.common.entity;

import java.util.ArrayList;
import java.util.List;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public String name;

  @Version public Integer version;

  @Association public List<User> userList = new ArrayList<>();
}
