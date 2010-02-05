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
package demo.smart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.seasar.doma.jdbc.SelectOptions;

import demo.smart.dao.ProductDao;
import demo.smart.entity.Product;

public class ProductService {

    @Resource
    protected ProductDao productDao;

    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDao
                .getProductsByCategory(categoryId, SelectOptions.get());
    }

    public List<Product> searchProductList(String keywords) {
        List<String> keywordList = new ArrayList<String>();
        for (StringTokenizer tokenizer = new StringTokenizer(keywords
                .toLowerCase(), " ", false); tokenizer.hasMoreTokens();) {
            keywordList.add(tokenizer.nextToken());
        }
        return productDao.searchProductList(keywordList, SelectOptions.get());
    }

}