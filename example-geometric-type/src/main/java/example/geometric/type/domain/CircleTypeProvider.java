package example.geometric.type.domain;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.postgresql.geometric.PGcircle;
import org.postgresql.geometric.PGpoint;
import org.seasar.doma.ExternalDomain;
import org.seasar.doma.jdbc.domain.JdbcTypeProvider;
import org.seasar.doma.jdbc.type.AbstractJdbcType;
import org.seasar.doma.jdbc.type.JdbcType;

@ExternalDomain
public class CircleTypeProvider extends JdbcTypeProvider<Circle> {

  private static final CircleType circleType = new CircleType();

  @Override
  public JdbcType<Circle> getJdbcType() {
    return circleType;
  }
}

class CircleType extends AbstractJdbcType<Circle> {

  protected CircleType() {
    super(Types.OTHER);
  }

  @Override
  protected Circle doGetValue(ResultSet resultSet, int index) throws SQLException {
    PGcircle c = resultSet.getObject(index, PGcircle.class);
    return toCircle(c);
  }

  @Override
  protected void doSetValue(PreparedStatement preparedStatement, int index, Circle value)
      throws SQLException {
    PGpoint p = new PGpoint(value.center().x(), value.center().y());
    PGcircle c = new PGcircle(p, value.radius());
    preparedStatement.setObject(index, c);
  }

  @Override
  protected Circle doGetValue(CallableStatement callableStatement, int index) throws SQLException {
    PGcircle c = callableStatement.getObject(index, PGcircle.class);
    return toCircle(c);
  }

  @Override
  protected String doConvertToLogFormat(Circle value) {
    return value.toString();
  }

  static Circle toCircle(PGcircle c) {
    if (c == null) {
      return null;
    }
    Point center = PointType.toPoint(c.center);
    return new Circle(center, c.radius);
  }
}
