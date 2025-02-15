package example.criteria.repository;

import example.common.entity.Category_;
import example.common.entity.Order;
import example.common.entity.OrderItem_;
import example.common.entity.Order_;
import example.common.entity.Payment_;
import example.common.entity.ProductCategory_;
import example.common.entity.Product_;
import example.common.entity.User_;
import java.util.Objects;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.QueryDsl;

public class OrderRepository {

  private final QueryDsl queryDsl;

  public OrderRepository(Config config) {
    Objects.requireNonNull(config);
    queryDsl = new QueryDsl(config);
  }

  public Order selectById(Integer id) {
    Order_ o = new Order_();
    Payment_ pa = new Payment_();
    User_ u = new User_();
    OrderItem_ oi = new OrderItem_();
    Product_ pr = new Product_();
    ProductCategory_ pc = new ProductCategory_();
    Category_ c = new Category_();

    return queryDsl
        .from(o)
        .leftJoin(pa, on -> on.eq(o.id, pa.orderId))
        .leftJoin(u, on -> on.eq(o.userId, u.id))
        .leftJoin(oi, on -> on.eq(o.id, oi.orderId))
        .leftJoin(pr, on -> on.eq(oi.productId, pr.id))
        .leftJoin(pc, on -> on.eq(pr.id, pc.productId))
        .leftJoin(c, on -> on.eq(pc.categoryId, c.id))
        .where(where -> where.eq(o.id, id))
        .associate(
            o,
            pa,
            (order, payment) -> {
              order.payment = payment;
              payment.order = order;
            })
        .associate(o, u, (order, user) -> order.user = user)
        .associate(
            o,
            oi,
            (order, orderItem) -> {
              order.orderItemList.add(orderItem);
              orderItem.order = order;
            })
        .associate(oi, pr, (orderItem, product) -> orderItem.product = product)
        .associate(
            pr,
            c,
            (product, category) -> {
              product.categoryList.add(category);
              category.productList.add(product);
            })
        .fetchOne();
  }
}
