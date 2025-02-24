package example.common.entity;

import org.seasar.doma.Association;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Version;

@Entity(metamodel = @Metamodel)
public class UserRole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public Integer userId;
  public Integer roleId;

  @Version public Integer version;

  @Association public User user;
  @Association public Role role;
}
