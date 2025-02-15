package example.sql.file.dao;

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

@Dao
public interface OrderDao {

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
