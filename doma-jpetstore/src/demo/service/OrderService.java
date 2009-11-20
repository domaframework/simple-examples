package demo.service;

import java.util.List;

import demo.dao.ItemDao;
import demo.dao.OrderDao;
import demo.dao.OrderLineItemDao;
import demo.dao.impl.ItemDaoImpl;
import demo.dao.impl.OrderDaoImpl;
import demo.dao.impl.OrderLineItemDaoImpl;
import demo.entity.Order;
import demo.entity.OrderLineItem;

public class OrderService {

    protected ItemDao itemDao = new ItemDaoImpl();

    protected OrderDao orderDao = new OrderDaoImpl();

    protected OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImpl();

    public void insertOrder(Order order, List<OrderLineItem> lineItems) {
        for (OrderLineItem lineItem : lineItems) {
            itemDao.updateInventoryQuantity(lineItem.itemId, lineItem.quantity);
        }

        orderDao.insertOrder(order);
        orderDao.insertOrderStatus(order);
        for (OrderLineItem lineItem : lineItems) {
            lineItem.orderId = order.orderId;
        }
        orderLineItemDao.insertLineItem(lineItems);
    }

    public Order getOrder(int orderId) {
        return orderDao.getOrder(orderId);
    }

    public List<OrderLineItem> getOrderLineItems(int orderId) {
        return orderLineItemDao.getLineItemsByOrderId(orderId);
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDao.getOrdersByUsername(username);
    }

}
