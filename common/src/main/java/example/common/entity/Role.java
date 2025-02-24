package example.common.entity;

import java.util.ArrayList;
import java.util.List;
import org.seasar.doma.*;

@Entity(metamodel = @Metamodel)
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public String name;

  @Version public Integer version;

  @Association public List<User> userList = new ArrayList<>();
}
