package demo.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import demo.entity.OrderLineItem;
import demo.entity.Order;
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
        Map<Object, Object> sessionMap = ExternalContextUtil.getSessionMap();
        PurchaseOrder purchaseOrder = (PurchaseOrder) sessionMap
                .get(SessionKeys.PURCHASE_ORDER);
        if (purchaseOrder == null) {
            return new PurchaseOrder();
        }
        return purchaseOrder;
    }

    public static void put(PurchaseOrder purchaseOrder) {
        Map<Object, Object> sessionMap = ExternalContextUtil.getSessionMap();
        sessionMap.put(SessionKeys.PURCHASE_ORDER, purchaseOrder);
    }

    public static void clear() {
        Map<Object, Object> sessionMap = ExternalContextUtil.getSessionMap();
        sessionMap.remove(SessionKeys.PURCHASE_ORDER);
    }

}
