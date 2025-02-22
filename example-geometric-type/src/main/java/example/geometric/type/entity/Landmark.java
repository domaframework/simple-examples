package example.geometric.type.entity;

import example.geometric.type.domain.Point;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;

@Entity(metamodel = @Metamodel)
public class Landmark {
  @Id public Integer id;
  public String name;
  public Point location;

  @Override
  public String toString() {
    return "Landmark [id=" + id + ", name=" + name + ", location=" + location + "]";
  }
}
