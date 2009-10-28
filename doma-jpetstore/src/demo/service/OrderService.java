package demo.service;

import java.util.List;

import com.ibatis.dao.client.DaoException;

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

    public void insertOrder(Order order) {
        // Get the next id within a separate transaction
        order.setOrderId(getNextId("ordernum"));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            itemDao.updateInventoryQuantity(itemId, increment);
        }

        orderDao.insertOrder(order);
        orderDao.insertOrderStatus(order);
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemDao.insertLineItem(lineItem);
        }

    }

    public Order getOrder(int orderId) {
        Order order = orderDao.getOrder(orderId);
        order.setLineItems(lineItemDao.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setItem(itemDao.getItem(lineItem.getItemId()));
        }

        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDao.getOrdersByUsername(username);
    }

    public synchronized int getNextId(String key) {
        Sequence sequence = sequenceDao.getSequence(key);
        if (sequence == null) {
            throw new DaoException(
                    "Error: A null sequence was returned from the database (could not get next "
                            + key + " sequence).");
        }
        sequence.setNextId(sequence.getNextId() + 1);
        sequenceDao.updateSequence(sequence);
        return sequence.getNextId();
    }

}
