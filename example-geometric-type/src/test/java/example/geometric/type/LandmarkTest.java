package example.geometric.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.geometric.type.dao.LandmarkDao;
import example.geometric.type.dao.LandmarkDaoImpl;
import example.geometric.type.domain.Point;
import example.geometric.type.entity.Landmark;
import example.geometric.type.entity.Landmark_;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.QueryDsl;

@ExtendWith(TestEnvironment.class)
public class LandmarkTest {

  @Test
  void testPoint_withSqlAnnotation(Config config) {
    LandmarkDao dao = new LandmarkDaoImpl(config);

    Landmark landmark = new Landmark();
    landmark.id = 1;
    landmark.name = "Tokyo Tower";
    landmark.location = new Point(35.6586, 139.7454);

    int count = dao.insert(landmark);
    assertEquals(1, count);

    Landmark entity = dao.selectById(1);
    assertNotNull(entity);
    assertEquals(landmark.id, entity.id);
    assertEquals(landmark.name, entity.name);
    assertEquals(landmark.location, entity.location);
  }

  @Test
  void testPoint_withCriteria(Config config) {
    QueryDsl dsl = new QueryDsl(config);
    Landmark_ l = new Landmark_();

    Landmark landmark = new Landmark();
    landmark.id = 1;
    landmark.name = "Tokyo Tower";
    landmark.location = new Point(35.6586, 139.7454);

    var result = dsl.insert(l).single(landmark).execute();
    assertEquals(1, result.getCount());

    Landmark entity = dsl.from(l).where(wh -> wh.eq(l.id, 1)).fetchOne();
    assertNotNull(entity);
    assertEquals(landmark.id, entity.id);
    assertEquals(landmark.name, entity.name);
    assertEquals(landmark.location, entity.location);
  }
}
