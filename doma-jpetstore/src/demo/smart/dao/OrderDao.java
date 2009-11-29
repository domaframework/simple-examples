package demo.smart.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;

import demo.config.AppConfig;
import demo.smart.entity.Order;

@Dao(config = AppConfig.class)
public interface OrderDao {

    @Select
    Order getOrder(int orderId);

    @Select
    List<Order> getOrdersByUsername(String username);

    @Insert
    int insertOrder(Order order);

    @Insert(sqlFile = true)
    int insertOrderStatus(Order order);

}
