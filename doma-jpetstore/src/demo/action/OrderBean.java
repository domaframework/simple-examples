package demo.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.struts.beanaction.ActionContext;

import demo.entity.Order;
import demo.service.AccountService;
import demo.service.OrderService;

public class OrderBean extends AbstractBean implements Serializable {

    private static final List CARD_TYPE_LIST;

    private Order order;
    private int orderId;
    private boolean shippingAddressRequired;
    private boolean confirmed;
    private List<Order> orderList;
    private String pageDirection;

    static {
        List cardList = new ArrayList();
        cardList.add("Visa");
        cardList.add("MasterCard");
        cardList.add("American Express");
        CARD_TYPE_LIST = Collections.unmodifiableList(cardList);
    }

    public OrderBean() {
        this(new AccountService(), new OrderService());
    }

    public OrderBean(AccountService accountService, OrderService orderService) {
        order = new Order();
        shippingAddressRequired = false;
        confirmed = false;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isShippingAddressRequired() {
        return shippingAddressRequired;
    }

    public void setShippingAddressRequired(boolean shippingAddressRequired) {
        this.shippingAddressRequired = shippingAddressRequired;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public List getCreditCardTypes() {
        return CARD_TYPE_LIST;
    }

    public String getPageDirection() {
        return pageDirection;
    }

    public void setPageDirection(String pageDirection) {
        this.pageDirection = pageDirection;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public String listOrders() {
        Map sessionMap = ActionContext.getActionContext().getSessionMap();
        AccountBean accountBean = (AccountBean) sessionMap.get("accountBean");
        // TODO
        orderList = getOrderService().getOrdersByUsername(
                accountBean.getAccount().getUsername());
        return SUCCESS;
    }

    public String switchOrderPage() {
        // TODO
        // if ("next".equals(pageDirection)) {
        // orderList.nextPage();
        // } else if ("previous".equals(pageDirection)) {
        // orderList.previousPage();
        // }
        return SUCCESS;
    }

    public String newOrderForm() {
        Map sessionMap = ActionContext.getActionContext().getSessionMap();
        AccountBean accountBean = (AccountBean) sessionMap.get("accountBean");
        CartBean cartBean = (CartBean) sessionMap.get("cartBean");

        clear();
        if (accountBean == null || !accountBean.isAuthenticated()) {
            setMessage("You must sign on before attempting to check out.  Please sign on and try checking out again.");
            return SIGNON;
        } else if (cartBean != null) {
            order.initOrder(accountBean.getAccount(), cartBean.getCart());
            return SUCCESS;
        } else {
            setMessage("An order could not be created because a cart could not be found.");
            return FAILURE;
        }
    }

    public String newOrder() {
        Map sessionMap = ActionContext.getActionContext().getSessionMap();

        if (shippingAddressRequired) {
            shippingAddressRequired = false;
            return SHIPPING;
        } else if (!isConfirmed()) {
            return CONFIRM;
        } else if (getOrder() != null) {

            getOrderService().insertOrder(order);

            CartBean cartBean = (CartBean) sessionMap.get("cartBean");
            cartBean.clear();

            setMessage("Thank you, your order has been submitted.");

            return SUCCESS;
        } else {
            setMessage("An error occurred processing your order (order was null).");
            return FAILURE;
        }
    }

    public String viewOrder() {
        Map sessionMap = ActionContext.getActionContext().getSessionMap();
        AccountBean accountBean = (AccountBean) sessionMap.get("accountBean");

        order = getOrderService().getOrder(orderId);

        if (accountBean.getAccount().getUsername().equals(order.getUsername())) {
            return SUCCESS;
        } else {
            order = null;
            setMessage("You may only view your own orders.");
            return FAILURE;
        }
    }

    @Override
    public void reset() {
        shippingAddressRequired = false;
    }

    public void clear() {
        order = new Order();
        orderId = 0;
        shippingAddressRequired = false;
        confirmed = false;
        orderList = null;
        pageDirection = null;
    }

    /**
     * @return the accountService
     */
    private AccountService getAccountService() {
        return new AccountService();
    }

    /**
     * @return the orderService
     */
    private OrderService getOrderService() {
        return new OrderService();
    }

}
