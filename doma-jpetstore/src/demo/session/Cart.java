package demo.session;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.entity.Item;
import demo.util.ExternalContextUtil;

public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<String, CartItem> itemMap = Collections
            .synchronizedMap(new HashMap<String, CartItem>());

    private final List<CartItem> itemList = new ArrayList<CartItem>();

    public List<CartItem> getCartItemList() {
        return itemList;
    }

    public CartItem getCartItem(String itemId) {
        return itemMap.get(itemId);
    }

    public int getNumberOfItems() {
        return itemList.size();
    }

    public boolean containsItemId(String itemId) {
        return itemMap.containsKey(itemId);
    }

    public void addItem(Item item, boolean isInStock) {
        CartItem cartItem = itemMap.get(item.itemId);
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(0);
            cartItem.setInStock(isInStock);
            itemMap.put(item.itemId, cartItem);
            itemList.add(cartItem);
        }
        cartItem.incrementQuantity();
    }

    public Item removeItemById(String itemId) {
        CartItem cartItem = itemMap.remove(itemId);
        if (cartItem == null) {
            return null;
        } else {
            itemList.remove(cartItem);
            return cartItem.getItem();
        }
    }

    public void incrementQuantityByItemId(String itemId) {
        CartItem cartItem = itemMap.get(itemId);
        cartItem.incrementQuantity();
    }

    public void setQuantityByItemId(String itemId, int quantity) {
        CartItem cartItem = itemMap.get(itemId);
        cartItem.setQuantity(quantity);
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = BigDecimal.ZERO;
        for (CartItem cartItem : itemList) {
            Item item = cartItem.getItem();
            BigDecimal listPrice = item.listPrice;
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem
                    .getQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }

    public static Cart get() {
        Map<Object, Object> sessionMap = ExternalContextUtil.getSessionMap();
        Cart cart = (Cart) sessionMap.get(SessionKeys.CART);
        if (cart == null) {
            return new Cart();
        }
        return cart;
    }

    public static void put(Cart cart) {
        Map<Object, Object> sessionMap = ExternalContextUtil.getSessionMap();
        sessionMap.put(SessionKeys.CART, cart);
    }

    public static void clear() {
        Map<Object, Object> sessionMap = ExternalContextUtil.getSessionMap();
        sessionMap.remove(SessionKeys.CART);
    }
}
