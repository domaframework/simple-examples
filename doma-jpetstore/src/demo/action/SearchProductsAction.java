package demo.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import demo.entity.Product;
import demo.form.SearchProductsForm;
import demo.service.ProductService;

public class SearchProductsAction {

    protected ProductService productService = new ProductService();

    @Resource
    @ActionForm
    protected SearchProductsForm searchProductsForm;

    // out
    public List<Product> productList;

    @Execute(validator = true, input = "productsjsp")
    public String search() {
        String keyword = searchProductsForm.keyword;
        productList = productService.searchProductList(keyword);
        return "products.jsp";
    }

}
