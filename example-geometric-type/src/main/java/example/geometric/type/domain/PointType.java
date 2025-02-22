package example.geometric.type.domain;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.postgresql.geometric.PGpoint;
import org.seasar.doma.jdbc.type.AbstractJdbcType;

class PointType extends AbstractJdbcType<Point> {

  protected PointType() {
    super(Types.OTHER);
  }

  @Override
  protected Point doGetValue(ResultSet resultSet, int index) throws SQLException {
    PGpoint p = resultSet.getObject(index, PGpoint.class);
    return toPoint(p);
  }

  @Override
  protected void doSetValue(PreparedStatement preparedStatement, int index, Point value)
      throws SQLException {
    PGpoint p = new PGpoint(value.x(), value.y());
    preparedStatement.setObject(index, p);
  }

  @Override
  protected Point doGetValue(CallableStatement callableStatement, int index) throws SQLException {
    PGpoint p = callableStatement.getObject(index, PGpoint.class);
    return toPoint(p);
  }

  @Override
  protected String doConvertToLogFormat(Point value) {
    return value.toString();
  }

  static Point toPoint(PGpoint p) {
    if (p == null) {
      return null;
    }
    return new Point(p.x, p.y);
  }
}
