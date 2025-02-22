package example.geometric.type.domain;

import org.seasar.doma.ExternalDomain;
import org.seasar.doma.jdbc.domain.JdbcTypeProvider;
import org.seasar.doma.jdbc.type.JdbcType;

@ExternalDomain
public class CircleTypeProvider extends JdbcTypeProvider<Circle> {

  private static final CircleType circleType = new CircleType();

  @Override
  public JdbcType<Circle> getJdbcType() {
    return circleType;
  }
}
