/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package demo.smart.service;

import java.util.List;

import javax.annotation.Resource;

import demo.smart.dao.ItemDao;
import demo.smart.dao.OrderDao;
import demo.smart.dao.OrderLineItemDao;
import demo.smart.entity.Order;
import demo.smart.entity.OrderLineItem;

public class OrderService {

    @Resource
    protected ItemDao itemDao;

    @Resource
    protected OrderDao orderDao;

    @Resource
    protected OrderLineItemDao orderLineItemDao;

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
