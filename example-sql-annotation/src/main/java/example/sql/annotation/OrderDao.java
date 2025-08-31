package example.sql.annotation;

import example.common.entity.Category;
import example.common.entity.Order;
import example.common.entity.OrderItem;
import example.common.entity.Payment;
import example.common.entity.Product;
import example.common.entity.User;
import java.util.function.BiFunction;
import org.seasar.doma.AggregateStrategy;
import org.seasar.doma.AssociationLinker;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;

@Dao
public interface OrderDao {

  @Sql(
      """
         SELECT /*%expand */*
           FROM "order" o
                LEFT OUTER JOIN payment pa
                             ON o.id = pa.order_id
                LEFT OUTER JOIN "user" u
                             ON o.user_id = u.id
                LEFT OUTER JOIN order_item oi
                             ON o.id = oi.order_id
                LEFT OUTER JOIN product pr
                             ON oi.product_id = pr.id
                LEFT OUTER JOIN product_category pc
                             ON pr.id = pc.product_id
                LEFT OUTER JOIN category c
                             ON pc.category_id = c.id
          WHERE o.id = /* id */0
         """)
  @Select(aggregateStrategy = OrderAggregateStrategy.class)
  Order selectById(Integer id);
}

@AggregateStrategy(root = Order.class, tableAlias = "o")
interface OrderAggregateStrategy {
  @AssociationLinker(propertyPath = "payment", tableAlias = "pa")
  BiFunction<Order, Payment, Order> payment =
      (order, payment) -> {
        order.payment = payment;
        payment.order = order;
        return order;
      };

  @AssociationLinker(propertyPath = "user", tableAlias = "u")
  BiFunction<Order, User, Order> user =
      (order, user) -> {
        order.user = user;
        return order;
      };

  @AssociationLinker(propertyPath = "orderItemList", tableAlias = "oi")
  BiFunction<Order, OrderItem, Order> orderItemList =
      (order, orderItem) -> {
        order.orderItemList.add(orderItem);
        orderItem.order = order;
        return order;
      };

  @AssociationLinker(propertyPath = "orderItemList.product", tableAlias = "pr")
  BiFunction<OrderItem, Product, OrderItem> orderItemList$product =
      (orderItem, product) -> {
        orderItem.product = product;
        return orderItem;
      };

  @AssociationLinker(propertyPath = "orderItemList.product.categoryList", tableAlias = "c")
  BiFunction<Product, Category, Product> orderItemList$product$category =
      (product, category) -> {
        product.categoryList.add(category);
        category.productList.add(product);
        return product;
      };
}
