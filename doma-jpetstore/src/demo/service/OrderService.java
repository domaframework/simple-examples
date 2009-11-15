package demo.service;

import java.util.List;

import demo.dao.ItemDao;
import demo.dao.LineItemDao;
import demo.dao.OrderDao;
import demo.dao.impl.ItemDaoImpl;
import demo.dao.impl.LineItemDaoImpl;
import demo.dao.impl.OrderDaoImpl;
import demo.entity.LineItem;
import demo.entity.Order;

public class OrderService {

    private ItemDao itemDao = new ItemDaoImpl();

    private OrderDao orderDao = new OrderDaoImpl();

    private LineItemDao lineItemDao = new LineItemDaoImpl();

    public void insertOrder(Order order, List<LineItem> lineItems) {
        for (LineItem lineItem : lineItems) {
            itemDao.updateInventoryQuantity(lineItem.itemId, lineItem.quantity);
        }

        orderDao.insertOrder(order);
        orderDao.insertOrderStatus(order);
        for (LineItem lineItem : lineItems) {
            lineItem.orderId = order.orderId;
            lineItemDao.insertLineItem(lineItem);
        }

    }

    public Order getOrder(int orderId) {
        return orderDao.getOrder(orderId);
    }

    public List<LineItem> getOrderLineItems(int orderId) {
        return lineItemDao.getLineItemsByOrderId(orderId);
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDao.getOrdersByUsername(username);
    }

}
