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
package demo.smart.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import demo.smart.entity.Order;
import demo.smart.entity.OrderLineItem;
import demo.smart.util.ExternalContextUtil;

public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Order order;

    protected List<OrderLineItem> lineItems = new ArrayList<OrderLineItem>();

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public static PurchaseOrder get() {
        PurchaseOrder purchaseOrder = (PurchaseOrder) ExternalContextUtil
                .getSession().getAttribute(SessionKeys.PURCHASE_ORDER);
        if (purchaseOrder == null) {
            return new PurchaseOrder();
        }
        return purchaseOrder;
    }

    public static void put(PurchaseOrder purchaseOrder) {
        ExternalContextUtil.getSession().setAttribute(
                SessionKeys.PURCHASE_ORDER, purchaseOrder);
    }

    public static void clear() {
        ExternalContextUtil.getSession().removeAttribute(
                SessionKeys.PURCHASE_ORDER);
    }

}
