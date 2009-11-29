package demo.smart.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import demo.config.AppConfig;
import demo.smart.entity.Item;

@Dao(config = AppConfig.class)
public interface ItemDao {

    @Update(sqlFile = true)
    int updateInventoryQuantity(String itemId, Integer increment);

    @Select
    Integer getInventoryQuantity(String itemId);

    @Select
    Item getItem(String itemId);

    @Select
    List<Item> getItemsByProduct(String productId);

}
