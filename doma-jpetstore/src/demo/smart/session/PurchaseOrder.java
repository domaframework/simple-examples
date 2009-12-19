package demo.smart.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import demo.session.SessionKeys;
import demo.smart.entity.Order;
import demo.smart.entity.OrderLineItem;
import demo.util.ExternalContextUtil;

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
