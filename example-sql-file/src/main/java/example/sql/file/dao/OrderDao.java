package example.sql.file.dao;

import example.common.entity.Category;
import example.common.entity.Order;
import example.common.entity.OrderItem;
import example.common.entity.Payment;
import example.common.entity.Product;
import example.common.entity.User;

import java.util.function.BiConsumer;
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
  BiConsumer<Order, Payment> payment =
          (order, payment) -> {
            order.payment = payment;
            payment.order = order;
          };

  @AssociationLinker(propertyPath = "user", tableAlias = "u")
  BiConsumer<Order, User> user =
          (order, user) -> {
            order.user = user;
          };

  @AssociationLinker(propertyPath = "orderItemList", tableAlias = "oi")
  BiConsumer<Order, OrderItem> orderItemList =
          (order, orderItem) -> {
            order.orderItemList.add(orderItem);
            orderItem.order = order;
          };

  @AssociationLinker(propertyPath = "orderItemList.product", tableAlias = "pr")
  BiConsumer<OrderItem, Product> orderItemList$product =
          (orderItem, product) -> {
            orderItem.product = product;
          };

  @AssociationLinker(propertyPath = "orderItemList.product.categoryList", tableAlias = "c")
  BiConsumer<Product, Category> orderItemList$product$category =
          (product, category) -> {
            product.categoryList.add(category);
            category.productList.add(product);
          };
}
