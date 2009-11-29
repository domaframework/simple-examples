package demo.smart.service;

import java.util.List;

import demo.smart.dao.ItemDao;
import demo.smart.dao.OrderDao;
import demo.smart.dao.OrderLineItemDao;
import demo.smart.dao.impl.ItemDaoImpl;
import demo.smart.dao.impl.OrderDaoImpl;
import demo.smart.dao.impl.OrderLineItemDaoImpl;
import demo.smart.entity.Order;
import demo.smart.entity.OrderLineItem;

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
