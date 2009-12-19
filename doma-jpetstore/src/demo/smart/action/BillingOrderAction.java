package demo.smart.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import demo.action.Authorize;
import demo.smart.entity.Account;
import demo.smart.entity.Order;
import demo.smart.entity.OrderLineItem;
import demo.smart.form.BillingOrderForm;
import demo.smart.service.AccountService;
import demo.smart.session.Cart;
import demo.smart.session.CartItem;
import demo.smart.session.PurchaseOrder;
import demo.smart.session.User;
import demo.smart.util.TokenUtil;

@Authorize
public class BillingOrderAction {

    @Resource
    protected AccountService accountService;

    @ActionForm
    @Resource
    protected BillingOrderForm billingOrderForm;

    // out
    public Order order;

    // out
    public List<String> creditCardTypes = Constants.CARD_TYPE_LIST;

    @Execute(validator = false, input = "/signin/signinForm")
    public String newOrderForm() {
        User user = User.get();
        Cart cart = Cart.get();

        if (cart.getCartItemList().isEmpty()) {
            throw new ActionMessagesException(
                    "You must sign on before attempting to check out.  Please sign on and try checking out again.",
                    false);
        }

        Account account = accountService.getAccount(user.getUsername());
        Order order = createOrder(account, cart);
        List<OrderLineItem> lineItems = createLineItems(cart);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrder(order);
        purchaseOrder.setLineItems(lineItems);
        PurchaseOrder.put(purchaseOrder);

        Beans.copy(order, billingOrderForm).execute();

        return "billingOrderForm.jsp";
    }

    @Execute(validator = true, input = "billingOrderForm.jsp")
    public String continueOrder() {
        PurchaseOrder purchaseOrder = PurchaseOrder.get();
        order = purchaseOrder.getOrder();
        if (order == null) {
            throw new ActionMessagesException(
                    "An error occurred processing your order.", false);
        }
        Beans.copy(billingOrderForm, order).excludesNull().excludesWhitespace()
                .execute();
        PurchaseOrder.put(purchaseOrder);
        if (billingOrderForm.shippingAddressRequired) {
            Beans.copy(order, billingOrderForm).execute();
            return "/shippingOrder/newOrderForm";
        }

        TokenUtil.save();

        return "/order/confirmOrderForm.jsp";
    }

    protected Order createOrder(Account account, Cart cart) {
        Order order = new Order();
        order.username = account.username;
        order.orderDate = new Date(System.currentTimeMillis());

        order.shipToFirstName = account.firstName;
        order.shipToLastName = account.firstName;
        order.shipAddress1 = account.address1;
        order.shipAddress2 = account.address2;
        order.shipCity = account.city;
        order.shipState = account.state;
        order.shipZip = account.zip;
        order.shipCountry = account.country;

        order.billToFirstName = account.firstName;
        order.billToLastName = account.firstName;
        order.billAddress1 = account.address1;
        order.billAddress2 = account.address2;
        order.billCity = account.city;
        order.billState = account.state;
        order.billZip = account.zip;
        order.billCountry = account.country;

        order.totalPrice = cart.getSubTotal();

        order.creditCard = "999 9999 9999 9999";
        order.expiryDate = "12/03";
        order.cardType = "Visa";
        order.courier = "UPS";
        order.locale = "CA";
        order.status = "P";

        return order;
    }

    protected List<OrderLineItem> createLineItems(Cart cart) {
        ArrayList<OrderLineItem> lineItems = new ArrayList<OrderLineItem>();
        for (CartItem cartItem : cart.getCartItemList()) {
            OrderLineItem lineItem = new OrderLineItem();
            lineItem.lineNumber = lineItems.size() + 1;
            lineItem.quantity = cartItem.getQuantity();
            lineItem.itemId = cartItem.getItem().itemId;
            lineItem.unitPrice = cartItem.getItem().listPrice;
            lineItems.add(lineItem);
        }
        return lineItems;
    }

}