package example.geometric.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.geometric.type.dao.CircleZoneDao;
import example.geometric.type.dao.CircleZoneDaoImpl;
import example.geometric.type.domain.Circle;
import example.geometric.type.domain.Point;
import example.geometric.type.entity.CircleZone;
import example.geometric.type.entity.CircleZone_;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.QueryDsl;

@ExtendWith(TestEnvironment.class)
public class CircleZoneTest {

  @Test
  void testCircle_withSqlAnnotation(Config config) {
    CircleZoneDao dao = new CircleZoneDaoImpl(config);

    CircleZone circleZone = new CircleZone();
    circleZone.id = 1;
    circleZone.name = "Round Plaza";
    circleZone.boundary = new Circle(new Point(35.6804, 139.7690), 0.005);

    int count = dao.insert(circleZone);
    assertEquals(1, count);

    CircleZone entity = dao.selectById(1);
    assertNotNull(entity);
    assertEquals(circleZone.id, entity.id);
    assertEquals(circleZone.name, entity.name);
    assertEquals(circleZone.boundary, entity.boundary);
  }

  @Test
  void testCircle_withCriteria(Config config) {
    QueryDsl dsl = new QueryDsl(config);
    CircleZone_ c = new CircleZone_();

    CircleZone circleZone = new CircleZone();
    circleZone.id = 1;
    circleZone.name = "Round Plaza";
    circleZone.boundary = new Circle(new Point(35.6804, 139.7690), 0.005);

    var result = dsl.insert(c).single(circleZone).execute();
    assertEquals(1, result.getCount());

    CircleZone entity = dsl.from(c).where(wh -> wh.eq(c.id, 1)).fetchOne();
    assertNotNull(entity);
    assertEquals(circleZone.id, entity.id);
    assertEquals(circleZone.name, entity.name);
    assertEquals(circleZone.boundary, entity.boundary);
  }
}
