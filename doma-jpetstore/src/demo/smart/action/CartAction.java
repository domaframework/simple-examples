package demo.smart.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import demo.smart.entity.Item;
import demo.smart.form.CartForm;
import demo.smart.service.ItemService;
import demo.smart.session.Cart;
import demo.smart.session.CartItem;
import demo.smart.util.TokenUtil;

public class CartAction {

    @Resource
    protected ItemService itemService;

    @ActionForm
    @Resource
    protected CartForm cartForm;

    // out
    public Cart cart;

    @Execute(urlPattern = "addItemToCart/{itemId}", validator = true, input = "viewCart")
    public String addItemToCart() {
        String workingItemId = cartForm.itemId;
        cart = Cart.get();
        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            // isInStock is a "real-time" property that must be updated
            // every time an item is added to the cart, even if other
            // item details are cached.
            boolean isInStock = itemService.isItemInStock(workingItemId);
            Item item = itemService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }
        Cart.put(cart);
        return "viewCart?redirect=true";
    }

    @Execute(urlPattern = "removeItemFromCart/{itemId}", validator = true, input = "viewCart")
    public String removeItemFromCart() {
        String workingItemId = cartForm.itemId;
        cart = Cart.get();
        Item item = cart.removeItemById(workingItemId);
        if (item == null) {
            throw new ActionMessagesException(
                    "Attempted to remove null CartItem from Cart.", false);
        }
        Cart.put(cart);
        return "viewCart?redirect=true";
    }

    @Execute(validator = false)
    public String updateCartQuantities() {
        cart = Cart.get();
        for (Map.Entry<String, String> entry : cartForm.itemIds.entrySet()) {
            String itemId = entry.getKey();
            CartItem cartItem = cart.getCartItem(itemId);
            if (cartItem == null) {
                continue;
            }
            String quantity = entry.getValue();
            int qty = 0;
            try {
                qty = Integer.valueOf(quantity);
            } catch (NumberFormatException e) {
                continue;
            }
            if (qty < 1) {
                cart.removeItemById(itemId);
            } else {
                cart.setQuantityByItemId(itemId, qty);
            }
        }
        Cart.put(cart);
        return "viewCart?redirect=true";
    }

    @Execute(validator = false)
    public String viewCart() {
        cart = Cart.get();
        return "cart.jsp";
    }

    @Execute(validator = false)
    public String checkout() {
        cart = Cart.get();

        TokenUtil.save();

        return "checkoutForm.jsp";
    }

    @Execute(validator = false, validate = "validateToken", input = "checkoutForm.jsp")
    public String continueCheckout() {
        return "/billingOrder/newOrderForm";
    }

    public ActionMessages validateToken() {
        return TokenUtil.validate();
    }

}
