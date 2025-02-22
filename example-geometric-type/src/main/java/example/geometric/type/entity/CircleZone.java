package example.geometric.type.entity;

import example.geometric.type.domain.Circle;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;

@Entity(metamodel = @Metamodel)
public class CircleZone {
  @Id public Integer id;
  public String name;
  public Circle boundary;

  @Override
  public String toString() {
    return "CircleZone [id=" + id + ", name=" + name + ", boundary=" + boundary + "]";
  }
}
