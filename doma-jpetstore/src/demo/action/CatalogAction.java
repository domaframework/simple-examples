package demo.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

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

    @Execute(urlPattern = "viewCategory/{id}", validator = false, input = "/")
    public String viewCategory() {
        category = catalogService.getCategory(catalogForm.id);
        productList = catalogService.getProductListByCategory(catalogForm.id);
        return "category.jsp";
    }

    @Execute(urlPattern = "viewProduct/{id}", validator = false, input = "/")
    public String viewProduct() {
        product = catalogService.getProduct(catalogForm.id);
        itemList = itemService.getItemsByProduct(catalogForm.id);
        return "product.jsp";
    }

    @Execute(urlPattern = "viewItem/{id}", validator = false, input = "/")
    public String viewItem() {
        item = itemService.getItem(catalogForm.id);
        return "item.jsp";
    }

}
