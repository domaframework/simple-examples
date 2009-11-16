package demo.action;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import demo.entity.Item;
import demo.form.CartForm;
import demo.service.CatalogService;
import demo.service.ItemService;
import demo.session.Cart;
import demo.session.CartItem;
import demo.util.ExternalContextUtil;

public class CartAction {

    protected CatalogService catalogService = new CatalogService();

    protected ItemService itemService = new ItemService();

    @ActionForm
    @Resource
    protected CartForm cartForm;

    // out
    public Cart cart;

    @Execute(urlPattern = "addItemToCart/{itemId}", validator = false)
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

    @Execute(urlPattern = "removeItemFromCart/{itemId}", validator = false, input = "cart.jsp")
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

    @Execute(validator = false, input = "cart.jsp")
    public String updateCartQuantities() {
        cart = Cart.get();
        Map<Object, Object> paramMap = ExternalContextUtil.getParamMap();
        for (Iterator<CartItem> it = cart.getCartItemList().iterator(); it
                .hasNext();) {
            CartItem cartItem = it.next();
            Item item = cartItem.getItem();
            try {
                int quantity = Integer.parseInt((String) paramMap
                        .get(item.itemId));
                cart.setQuantityByItemId(item.itemId, quantity);
                if (quantity < 1) {
                    it.remove();
                }
            } catch (Exception ignored) {
                // ignore parse exceptions on purpose
            }
        }
        Cart.put(cart);
        return "viewCart?redirect=true";
    }

    @Execute(validator = false)
    public String checkout() {
        cart = Cart.get();
        return "checkout.jsp";
    }

    @Execute(validator = false)
    public String viewCart() {
        cart = Cart.get();
        return "cart.jsp";
    }

}
