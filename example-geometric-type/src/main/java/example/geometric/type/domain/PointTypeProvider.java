package example.geometric.type.domain;

import org.seasar.doma.ExternalDomain;
import org.seasar.doma.jdbc.domain.JdbcTypeProvider;
import org.seasar.doma.jdbc.type.JdbcType;

@ExternalDomain
public class PointTypeProvider extends JdbcTypeProvider<Point> {

  private static final PointType pointType = new PointType();

  @Override
  public JdbcType<Point> getJdbcType() {
    return pointType;
  }
}
