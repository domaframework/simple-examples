package demo.smart.session;

import java.io.Serializable;

import demo.smart.domain.Amount;
import demo.smart.entity.Item;

public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Item item;
    private int quantity;
    private boolean inStock;
    private Amount total;

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public Amount getTotal() {
        return total;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        calculateTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotal();
    }

    public void incrementQuantity() {
        quantity++;
        calculateTotal();
    }

    private void calculateTotal() {
        if (item != null && item.listPrice != null) {
            total = item.listPrice.multiply(quantity);
        } else {
            total = null;
        }
    }

}
