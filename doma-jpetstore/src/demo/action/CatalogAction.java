package demo.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import demo.entity.Category;
import demo.entity.Item;
import demo.entity.Product;
import demo.form.CatalogForm;
import demo.service.CatalogService;
import demo.service.ItemService;

public class CatalogAction {

    protected CatalogService catalogService = new CatalogService();

    protected ItemService itemService = new ItemService();

    @ActionForm
    @Resource
    protected CatalogForm catalogForm;

    // out
    public Category category;

    // out
    public List<Product> productList;

    // out
    public Product product;

    // out
    public List<Item> itemList;

    // out
    public Item item;

    @Execute(urlPattern = "viewCategory/{categoryId}", validator = false, input = "/")
    public String viewCategory() {
        category = catalogService.getCategory(catalogForm.categoryId);
        productList = catalogService
                .getProductListByCategory(catalogForm.categoryId);
        return "category.jsp";
    }

    @Execute(urlPattern = "viewProduct/{productId}", validator = false, input = "/")
    public String viewProduct() {
        product = catalogService.getProduct(catalogForm.productId);
        itemList = itemService.getItemsByProduct(catalogForm.productId);
        return "product.jsp";
    }

    @Execute(urlPattern = "viewItem/{itemId}", validator = false, input = "/")
    public String viewItem() {
        item = itemService.getItem(catalogForm.itemId);
        return "item.jsp";
    }

    @Execute(input = "/")
    public String searchProducts() {
        String keyword = catalogForm.keyword;
        if (keyword == null || keyword.length() < 1) {
            throw new ActionMessagesException(
                    "Please enter a keyword to search for, then press the search button.",
                    false);
        } else {
            productList = catalogService.searchProductList(keyword);
        }
        return "searchProducts.jsp";
    }

    public void validateSearchKeyword(String keyword, String password) {
    }

}
