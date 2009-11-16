package demo.service;

import java.util.List;

import demo.dao.ItemDao;
import demo.dao.impl.ItemDaoImpl;
import demo.entity.Item;

public class ItemService {

    private ItemDao itemDao = new ItemDaoImpl();

    public boolean isItemInStock(String itemId) {
        Integer i = itemDao.getInventoryQuantity(itemId);
        return (i != null && i.intValue() > 0);
    }

    public Item getItem(String itemId) {
        return itemDao.getItem(itemId);
    }

    public List<Item> getItemsByProduct(String productId) {
        return itemDao.getItemsByProduct(productId);
    }
}
