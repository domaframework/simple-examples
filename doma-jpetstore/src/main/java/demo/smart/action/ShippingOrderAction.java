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

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import demo.action.Authorize;
import demo.smart.entity.Order;
import demo.smart.form.ShippingOrderForm;
import demo.smart.session.Cart;
import demo.smart.session.PurchaseOrder;
import demo.smart.util.TokenUtil;

@Authorize
public class ShippingOrderAction {

    @ActionForm
    @Resource
    protected ShippingOrderForm shippingOrderForm;

    // out
    public Order order;

    @Execute(validator = false, input = "/account/signinForm")
    public String newOrderForm() {
        Cart cart = Cart.get();

        if (cart.getCartItemList().isEmpty()) {
            throw new ActionMessagesException(
                    "You must sign on before attempting to check out.  Please sign on and try checking out again.",
                    false);
        }

        PurchaseOrder purchaseOrder = PurchaseOrder.get();
        order = purchaseOrder.getOrder();
        Beans.copy(order, shippingOrderForm).execute();

        return "shippingOrderForm.jsp";
    }

    @Execute(validator = true, input = "shippingOrderForm.jsp")
    public String continueOrder() {
        PurchaseOrder purchaseOrder = PurchaseOrder.get();
        order = purchaseOrder.getOrder();
        if (order == null) {
            throw new ActionMessagesException(
                    "An error occurred processing your order.", false);
        }
        Beans.copy(shippingOrderForm, order).excludesNull()
                .excludesWhitespace().execute();
        PurchaseOrder.put(purchaseOrder);

        TokenUtil.save();

        return "/order/confirmOrderForm.jsp";
    }

}