package demo.service;

import demo.dao.ItemDao;
import demo.dao.impl.ItemDaoImpl;
import demo.entity.Item;

public class ItemService {

    private ItemDao itemDao = new ItemDaoImpl();

    public ItemService() {
        this.itemDao = new ItemDaoImpl();
    }

    public boolean isItemInStock(String itemId) {
        Integer i = itemDao.getInventoryQuantity(itemId);
        return (i != null && i.intValue() > 0);
    }

    public Item getItem(String itemId) {
        Integer i = itemDao.getInventoryQuantity(itemId);
        Item item = itemDao.getItem(itemId);
        item.setQuantity(i.intValue());
        return item;
    }
}
