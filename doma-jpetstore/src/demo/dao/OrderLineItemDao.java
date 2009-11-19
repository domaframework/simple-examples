package demo.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;

import demo.config.AppConfig;
import demo.entity.OrderLineItem;

@Dao(config = AppConfig.class)
public interface OrderLineItemDao {

    @Select
    List<OrderLineItem> getLineItemsByOrderId(int orderId);

    @Insert
    int insertLineItem(OrderLineItem lineItem);
}
