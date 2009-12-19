package demo.smart.service;

import java.util.List;

import javax.annotation.Resource;

import demo.smart.dao.ItemDao;
import demo.smart.entity.Item;

public class ItemService {

    @Resource
    protected ItemDao itemDao;

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
