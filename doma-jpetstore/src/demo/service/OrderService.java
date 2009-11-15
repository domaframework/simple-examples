package demo.service;

import java.util.List;

import demo.dao.ItemDao;
import demo.dao.LineItemDao;
import demo.dao.OrderDao;
import demo.dao.SequenceDao;
import demo.dao.impl.ItemDaoImpl;
import demo.dao.impl.LineItemDaoImpl;
import demo.dao.impl.OrderDaoImpl;
import demo.dao.impl.SequenceDaoImpl;
import demo.entity.LineItem;
import demo.entity.Order;
import demo.entity.Sequence;

public class OrderService {

    private ItemDao itemDao;
    private OrderDao orderDao;
    private SequenceDao sequenceDao;
    private LineItemDao lineItemDao;

    public OrderService() {
        itemDao = new ItemDaoImpl();
        sequenceDao = new SequenceDaoImpl();
        orderDao = new OrderDaoImpl();
        lineItemDao = new LineItemDaoImpl();
    }

    public void insertOrder(Order order, List<LineItem> lineItems) {
        // Get the next id within a separate transaction
        order.orderId = getNextId("ordernum");

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

    public synchronized int getNextId(String key) {
        Sequence sequence = sequenceDao.getSequence(key);
        if (sequence == null) {
            throw new RuntimeException(
                    "Error: A null sequence was returned from the database (could not get next "
                            + key + " sequence).");
        }
        sequence.nextId = sequence.nextId + 1;
        sequenceDao.updateSequence(sequence);
        return sequence.nextId;
    }

}
