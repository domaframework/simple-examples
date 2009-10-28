package demo.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;

import demo.config.AppConfig;
import demo.entity.LineItem;

@Dao(config = AppConfig.class)
public interface LineItemDao {

    @Select
    List<LineItem> getLineItemsByOrderId(int orderId);

    @Insert(sqlFile = true)
    int insertLineItem(LineItem lineItem);
}
