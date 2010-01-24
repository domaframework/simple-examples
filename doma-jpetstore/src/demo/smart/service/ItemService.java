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

import java.util.List;

import javax.annotation.Resource;

import demo.smart.dao.ItemDao;
import demo.smart.entity.Item;

public class ItemService {

    @Resource
    protected ItemDao itemDao;

    public boolean isItemInStock(String itemId) {
        Integer i = itemDao.getInventoryQuantity(itemId);
        return (i != null && i.intValue() > 0);
    }

    public Item getItem(String itemId) {
        return itemDao.getItem(itemId);
    }

    public List<Item> getItemsByProduct(String productId) {
        return itemDao.getItemsByProduct(productId);
    }
}
