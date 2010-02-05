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
package demo.smart.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import demo.smart.domain.Amount;

@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String itemId;

    @Column(name = "PRODUCTID")
    public String productId;

    @Column(insertable = false, updatable = false)
    public String productName;

    @Column(insertable = false, updatable = false)
    public String productCategoryId;

    @Column(insertable = false, updatable = false)
    public String productDescription;

    public Amount listPrice;

    public BigDecimal unitCost;

    @Column(name = "SUPPLIER")
    public int supplierId;

    public String status;

    @Column(name = "ATTR1")
    public String attribute1;

    @Column(name = "ATTR2")
    public String attribute2;

    @Column(name = "ATTR3")
    public String attribute3;

    @Column(name = "ATTR4")
    public String attribute4;

    @Column(name = "ATTR5")
    public String attribute5;

    @Column(name = "QTY", insertable = false, updatable = false)
    public Integer quantity;

}
