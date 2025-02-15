package example.sql.annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import example.common.entity.OrderItem;
import example.common.entity.Product;
import example.common.test.TestEnvironment;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
public class SelectAggregateTest {

  @Test
  public void selectOrderAggregate(Config config) {
    OrderDao dao = new OrderDaoImpl(config);
    var order = dao.selectById(2);

    assertNotNull(order);
    assertEquals("Shipped", order.status);

    assertNotNull(order.payment);
    assertEquals(new BigDecimal("1398.98"), order.payment.amount);

    assertNotNull(order.user);
    assertEquals("Bob", order.user.name);

    assertEquals(1, order.orderItemList.size());
    OrderItem orderItem = order.orderItemList.get(0);
    assertEquals(order, orderItem.order);
    assertEquals(2, orderItem.quantity);

    Product product = orderItem.product;
    assertNotNull(product);
    assertEquals("Smartphone", product.name);

    assertEquals(2, product.categoryList.size());
    List<String> categoryNames = product.categoryList.stream().map(c -> c.name).toList();
    assertTrue(categoryNames.contains("Electronics"));
    assertTrue(categoryNames.contains("Gadgets"));
  }

  @Test
  public void selectReviewAggregate(Config config) {
    ReviewDao dao = new ReviewDaoImpl(config);
    var reviewList = dao.selectAll();
    assertEquals(3, reviewList.size());
    {
      var review = reviewList.get(0);
      assertNotNull(review);
      var user = review.user;
      assertNotNull(user);
      assertEquals("Alice", user.name);
      var product = review.product;
      assertNotNull(product);
      assertEquals("Laptop", product.name);
    }
    {
      var review = reviewList.get(1);
      assertNotNull(review);
      var user = review.user;
      assertNotNull(user);
      assertEquals("Bob", user.name);
      var product = review.product;
      assertNotNull(product);
      assertEquals("Smartphone", product.name);
    }
    {
      var review = reviewList.get(2);
      assertNotNull(review);
      var user = review.user;
      assertNotNull(user);
      assertEquals("Charlie", user.name);
      var product = review.product;
      assertNotNull(product);
      assertEquals("Tablet", product.name);
    }
  }
}
