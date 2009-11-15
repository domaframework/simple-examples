package demo.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;

import demo.entity.Account;
import demo.entity.LineItem;
import demo.entity.Order;
import demo.form.OrderForm;
import demo.service.AccountService;
import demo.service.CatalogService;
import demo.service.OrderService;
import demo.session.Cart;
import demo.session.CartItem;
import demo.session.PurchaseOrder;
import demo.session.Signin;
import demo.util.NumberUtil;

public class OrderAction {

    static List<String> CARD_TYPE_LIST;

    static {
        List<String> cardList = new ArrayList<String>();
        cardList.add("Visa");
        cardList.add("MasterCard");
        cardList.add("American Express");
        CARD_TYPE_LIST = Collections.unmodifiableList(cardList);
    }

    protected CatalogService catalogService = new CatalogService();

    protected AccountService accountService = new AccountService();

    protected OrderService orderService = new OrderService();

    @ActionForm
    @Resource
    protected OrderForm orderForm;

    // out
    public Order order;

    // out
    public List<LineItem> lineItems;

    // out
    public List<Order> orderList;

    // out
    public List<String> creditCardTypes = CARD_TYPE_LIST;

    @Execute(validator = false)
    public String listOrders() {
        Signin signin = Signin.get();
        orderList = orderService.getOrdersByUsername(signin.getUsername());
        return "listOrders.jsp";
    }

    @Execute(urlPattern = "viewOrder/{orderId}", validator = false, input = "listOrders")
    public String viewOrder() {
        if (!NumberUtil.isDigit(orderForm.orderId)) {
            throw new ActionMessagesException("Illegal orderId.", false);
        }
        int orderId = Integer.valueOf(orderForm.orderId);
        order = orderService.getOrder(orderId);
        lineItems = orderService.getOrderLineItems(orderId);

        if (order == null || lineItems == null) {
            throw new ActionMessagesException("An error occurred.", false);
        }

        Signin signin = Signin.get();
        if (signin.isAuthenticated()
                && signin.getUsername().equals(order.username)) {
            return "viewOrder.jsp";
        }
        throw new ActionMessagesException("You may only view your own orders.",
                false);
    }

    @Execute(validator = false, input = "/account/signinForm")
    public String newOrderForm() {
        Signin signin = Signin.get();
        Cart cart = Cart.get();

        if (!signin.isAuthenticated()) {
            throw new ActionMessagesException(
                    "You must sign on before attempting to check out.  Please sign on and try checking out again.",
                    false);
        }
        if (cart.getCartItemList().isEmpty()) {
            throw new ActionMessagesException(
                    "You must sign on before attempting to check out.  Please sign on and try checking out again.",
                    false);
        }

        Account account = accountService.getAccount(signin.getUsername());
        Order order = createOrder(account, cart);
        List<LineItem> lineItems = createLineItems(cart);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrder(order);
        purchaseOrder.setLineItems(lineItems);
        PurchaseOrder.put(purchaseOrder);

        Beans.copy(order, orderForm).execute();

        return "newOrderForm.jsp";
    }

    @Execute(validate = "validateNewOrder", input = "newOrderForm.jsp")
    public String newOrder() {
        PurchaseOrder purchaseOrder = PurchaseOrder.get();
        order = purchaseOrder.getOrder();
        if (order == null) {
            throw new ActionMessagesException(
                    "An error occurred processing your order.", false);
        }
        Beans.copy(orderForm, order).excludesNull().excludesWhitespace()
                .execute();
        PurchaseOrder.put(purchaseOrder);
        if (orderForm.shippingAddressRequired) {
            Beans.copy(order, orderForm).execute();
            return "shippingForm.jsp";
        }
        return "confirmOrder.jsp";
    }

    public ActionMessages validateNewOrder() {
        ActionMessages errors = new ActionMessages();
        if (orderForm.cardType == null || orderForm.cardType.isEmpty()) {
            errors.add("cardType", new ActionMessage("errors.required",
                    "Card Type"));
        }
        if (orderForm.creditCard == null || orderForm.creditCard.isEmpty()) {
            errors.add("creditCard", new ActionMessage("errors.required",
                    "Card Number"));
        }
        if (orderForm.expiryDate == null || orderForm.expiryDate.isEmpty()) {
            errors.add("expiryDate", new ActionMessage("errors.required",
                    "Expiry Date"));
        }
        if (orderForm.billToFirstName == null
                || orderForm.billToFirstName.isEmpty()) {
            errors.add("billToFirstName", new ActionMessage("errors.required",
                    "First name"));
        }
        if (orderForm.billToLastName == null
                || orderForm.billToLastName.isEmpty()) {
            errors.add("billToLastName", new ActionMessage("errors.required",
                    "Last name"));
        }
        if (orderForm.billAddress1 == null || orderForm.billAddress1.isEmpty()) {
            errors.add("billAddress1", new ActionMessage("errors.required",
                    "Address 1"));
        }
        if (orderForm.billCity == null || orderForm.billCity.isEmpty()) {
            errors
                    .add("billCity", new ActionMessage("errors.required",
                            "City"));
        }
        if (orderForm.billState == null || orderForm.billState.isEmpty()) {
            errors.add("billState", new ActionMessage("errors.required",
                    "State"));
        }
        if (orderForm.billZip == null || orderForm.billZip.isEmpty()) {
            errors.add("billZip", new ActionMessage("errors.required", "Zip"));
        }
        return errors;
    }

    @Execute(validate = "validateNewOrderWithShippingAddress", input = "shippingForm.jsp")
    public String newOrderWithShippingAddress() {
        PurchaseOrder purchaseOrder = PurchaseOrder.get();
        order = purchaseOrder.getOrder();
        if (order == null) {
            throw new ActionMessagesException(
                    "An error occurred processing your order.", false);
        }
        Beans.copy(orderForm, order).excludesNull().excludesWhitespace()
                .execute();
        PurchaseOrder.put(purchaseOrder);
        return "confirmOrder.jsp";
    }

    public ActionMessages validateNewOrderWithShippingAddress() {
        ActionMessages errors = new ActionMessages();
        if (orderForm.shipToFirstName == null
                || orderForm.shipToFirstName.isEmpty()) {
            errors.add("shipToFirstName", new ActionMessage("errors.required",
                    "First name"));
        }
        if (orderForm.shipToLastName == null
                || orderForm.shipToLastName.isEmpty()) {
            errors.add("shipToLastName", new ActionMessage("errors.required",
                    "Last name"));
        }
        if (orderForm.shipAddress1 == null || orderForm.shipAddress1.isEmpty()) {
            errors.add("shipAddress1", new ActionMessage("errors.required",
                    "Address 1"));
        }
        if (orderForm.shipCity == null || orderForm.shipCity.isEmpty()) {
            errors
                    .add("shipCity", new ActionMessage("errors.required",
                            "City"));
        }
        if (orderForm.shipState == null || orderForm.shipState.isEmpty()) {
            errors.add("shipState", new ActionMessage("errors.required",
                    "State"));
        }
        if (orderForm.shipZip == null || orderForm.shipZip.isEmpty()) {
            errors.add("shipZip", new ActionMessage("errors.required", "Zip"));
        }
        return errors;
    }

    @Execute(validator = false, input = "/account/signinForm")
    public String confirm() {
        PurchaseOrder purchaseOrder = PurchaseOrder.get();
        Order order = purchaseOrder.getOrder();
        List<LineItem> lineItems = purchaseOrder.getLineItems();
        if (order == null || lineItems == null) {
            throw new ActionMessagesException(
                    "An error occurred processing your order.", false);
        }
        orderService.insertOrder(order, lineItems);
        Cart.clear();

        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "Thank you, your order has been submitted.", false));
        ActionMessagesUtil.addMessages(RequestUtil.getRequest(), messages);
        return "/";
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

    protected List<LineItem> createLineItems(Cart cart) {
        ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
        for (CartItem cartItem : cart.getCartItemList()) {
            LineItem lineItem = new LineItem();
            lineItem.lineNumber = lineItems.size() + 1;
            lineItem.quantity = cartItem.getQuantity();
            lineItem.itemId = cartItem.getItem().itemId;
            lineItem.unitPrice = cartItem.getItem().listPrice;
            lineItems.add(lineItem);
        }
        return lineItems;
    }

}