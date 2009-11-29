package demo.smart.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;
import org.seasar.struts.util.ActionMessagesUtil;

import demo.action.Authorize;
import demo.session.User;
import demo.smart.entity.Order;
import demo.smart.entity.OrderLineItem;
import demo.smart.form.OrderForm;
import demo.smart.service.OrderService;
import demo.smart.session.Cart;
import demo.smart.session.PurchaseOrder;
import demo.smart.util.ExternalContextUtil;
import demo.smart.util.TokenUtil;

@Authorize
public class OrderAction {

    protected OrderService orderService = new OrderService();

    @ActionForm
    @Resource
    protected OrderForm orderForm;

    // out
    public Order order;

    // out
    public List<OrderLineItem> lineItems;

    // out
    public List<Order> orderList;

    @Execute(validator = false)
    public String listOrders() {
        User user = User.get();
        orderList = orderService.getOrdersByUsername(user.getUsername());
        return "listOrders.jsp";
    }

    @Execute(urlPattern = "viewOrder/{orderId}", validator = true, input = "listOrders")
    public String viewOrder() {
        int orderId = Integer.valueOf(orderForm.orderId);
        order = orderService.getOrder(orderId);
        lineItems = orderService.getOrderLineItems(orderId);

        if (order == null || lineItems == null) {
            throw new ActionMessagesException(String.format(
                    "orderId[%s] is not found.", orderId), false);
        }

        User user = User.get();
        if (user.isAuthenticated() && user.getUsername().equals(order.username)) {
            return "viewOrder.jsp";
        }
        throw new ActionMessagesException("You may only view your own orders.",
                false);
    }

    @Execute(validator = false, validate = "validateToken", input = "confirmOrderForm.jsp")
    public String confirm() {
        PurchaseOrder purchaseOrder = PurchaseOrder.get();
        Order order = purchaseOrder.getOrder();
        List<OrderLineItem> lineItems = purchaseOrder.getLineItems();
        if (order == null || lineItems == null) {
            throw new ActionMessagesException(
                    "An error occurred processing your order.", false);
        }
        orderService.insertOrder(order, lineItems);
        Cart.clear();

        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "Thank you, your order has been submitted.", false));
        ActionMessagesUtil.addMessages(ExternalContextUtil.getSession(),
                messages);
        return "/";
    }

    public ActionMessages validateToken() {
        return TokenUtil.validate();
    }

}