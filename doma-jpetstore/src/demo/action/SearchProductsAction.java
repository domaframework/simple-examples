package demo.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import demo.entity.Product;
import demo.form.SearchProductsForm;
import demo.service.CatalogService;

public class SearchProductsAction {

    protected CatalogService catalogService = new CatalogService();

    @Resource
    @ActionForm
    protected SearchProductsForm searchProductsForm;

    // out
    public List<Product> productList;

    @Execute(input = "productsjsp")
    public String search() {
        String keyword = searchProductsForm.keyword;
        productList = catalogService.searchProductList(keyword);
        return "products.jsp";
    }

}
