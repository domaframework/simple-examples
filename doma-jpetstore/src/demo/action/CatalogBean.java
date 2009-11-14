package demo.action;

import java.io.Serializable;
import java.util.List;

import demo.entity.Category;
import demo.entity.Item;
import demo.entity.Product;
import demo.service.CatalogService;

public class CatalogBean extends AbstractBean implements Serializable {

    private CatalogService catalogService;

    private String keyword;
    private String pageDirection;

    private String categoryId;
    private Category category;
    private List<Category> categoryList;

    private String productId;
    private Product product;
    private List<Product> productList;

    private String itemId;
    private Item item;
    private List<Item> itemList;

    public CatalogBean() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPageDirection() {
        return pageDirection;
    }

    public void setPageDirection(String pageDirection) {
        this.pageDirection = pageDirection;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String viewCategory() {
        if (categoryId != null) {
            // TODO
            productList = getCatalogService().getProductListByCategory(
                    categoryId);
            category = getCatalogService().getCategory(categoryId);
        }
        return SUCCESS;
    }

    public String viewProduct() {
        if (productId != null) {
            // TODO
            itemList = getCatalogService().getItemListByProduct(productId);
            product = getCatalogService().getProduct(productId);
        }
        return SUCCESS;
    }

    public String viewItem() {
        item = getCatalogService().getItem(itemId);
        product = getCatalogService().getProduct(item.getProductId());
        return SUCCESS;
    }

    public String searchProducts() {
        if (keyword == null || keyword.length() < 1) {
            setMessage("Please enter a keyword to search for, then press the search button.");
            return FAILURE;
        } else {
            productList = getCatalogService().searchProductList(keyword);
            return SUCCESS;
        }
    }

    public String switchProductListPage() {
        // TODO
        // if ("next".equals(pageDirection)) {
        // productList.nextPage();
        // } else if ("previous".equals(pageDirection)) {
        // productList.previousPage();
        // }
        return SUCCESS;
    }

    public String switchItemListPage() {
        // TODO
        // if ("next".equals(pageDirection)) {
        // itemList.nextPage();
        // } else if ("previous".equals(pageDirection)) {
        // itemList.previousPage();
        // }
        return SUCCESS;
    }

    public void clear() {
        keyword = null;
        pageDirection = null;

        categoryId = null;
        category = null;
        categoryList = null;

        productId = null;
        product = null;
        productList = null;

        itemId = null;
        item = null;
        itemList = null;
    }

    /**
     * @return the catalogService
     */
    private CatalogService getCatalogService() {
        return new CatalogService();
    }

}
