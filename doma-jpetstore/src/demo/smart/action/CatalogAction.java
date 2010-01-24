/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package demo.smart.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import demo.smart.entity.Category;
import demo.smart.entity.Item;
import demo.smart.entity.Product;
import demo.smart.form.CatalogForm;
import demo.smart.service.CategoryService;
import demo.smart.service.ItemService;
import demo.smart.service.ProductService;

public class CatalogAction {

    @Resource
    protected CategoryService categoryService;

    @Resource
    protected ProductService productService;

    @Resource
    protected ItemService itemService;

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

    @Execute(urlPattern = "viewCategory/{id}", validator = true, input = "/")
    public String viewCategory() {
        category = categoryService.getCategory(catalogForm.id);
        productList = productService.getProductListByCategory(catalogForm.id);
        return "category.jsp";
    }

    @Execute(urlPattern = "viewProduct/{id}", validator = true, input = "/")
    public String viewProduct() {
        product = productService.getProduct(catalogForm.id);
        itemList = itemService.getItemsByProduct(catalogForm.id);
        return "product.jsp";
    }

    @Execute(urlPattern = "viewItem/{id}", validator = true, input = "/")
    public String viewItem() {
        item = itemService.getItem(catalogForm.id);
        return "item.jsp";
    }

}
