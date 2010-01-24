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

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import demo.smart.domain.Amount;

@Entity
@Table(name = "LINEITEM")
public class OrderLineItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public int orderId;

    @Id
    @Column(name = "LINENUM")
    public int lineNumber;

    public int quantity;

    public String itemId;

    public Amount unitPrice;

    @Column(insertable = false, updatable = false)
    public Amount listPrice;

    @Column(insertable = false, updatable = false)
    public String productName;

    @Column(insertable = false, updatable = false)
    public String attribute1;

    @Column(insertable = false, updatable = false)
    public String attribute2;

    @Column(insertable = false, updatable = false)
    public String attribute3;

    @Column(insertable = false, updatable = false)
    public String attribute4;

    @Column(insertable = false, updatable = false)
    public String attribute5;

    public Amount getTotal() {
        return listPrice.multiply(quantity);
    }
}
