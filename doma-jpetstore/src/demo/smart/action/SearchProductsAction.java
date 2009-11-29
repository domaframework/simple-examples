package demo.smart.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import demo.smart.entity.Product;
import demo.smart.form.SearchProductsForm;
import demo.smart.service.ProductService;

public class SearchProductsAction {

    protected ProductService productService = new ProductService();

    @Resource
    @ActionForm
    protected SearchProductsForm searchProductsForm;

    // out
    public List<Product> productList;

    @Execute(validator = true, input = "products.jsp")
    public String search() {
        String keyword = searchProductsForm.keyword;
        productList = productService.searchProductList(keyword);
        return "products.jsp";
    }

}
