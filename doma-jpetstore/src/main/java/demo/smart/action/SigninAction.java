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

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import demo.action.JPetStoreRequestProcessor;
import demo.smart.entity.Account;
import demo.smart.form.SigninForm;
import demo.smart.service.AccountService;
import demo.smart.session.Cart;
import demo.smart.session.PurchaseOrder;
import demo.smart.session.User;
import demo.smart.util.ExternalContextUtil;

public class SigninAction {

    @Resource
    protected AccountService accountService;

    @ActionForm
    @Resource
    protected SigninForm signinForm;

    // out
    public Account account;

    // out
    public String returnURL;

    @Execute(validator = false)
    public String signinForm() {
        returnURL = (String) ExternalContextUtil.getRequest().getAttribute(
                JPetStoreRequestProcessor.RETURN_URL);
        return "signinForm.jsp";
    }

    @Execute(validator = true, input = "signinForm.jsp")
    public String signin() {
        account = accountService.getAccount(signinForm.username,
                signinForm.password);
        if (account == null) {
            throw new ActionMessagesException(
                    "Invalid username or password. Signin failed.", false);
        }

        Cart cart = Cart.get();
        PurchaseOrder purchaseOrder = PurchaseOrder.get();

        ExternalContextUtil.invalidateSession();

        User user = new User();
        user.setUsername(account.username);
        user.setFirstName(account.firstName);
        user.setAuthorized(true);
        User.put(user);
        Cart.put(cart);
        PurchaseOrder.put(purchaseOrder);
        if (signinForm.returnURL != null
                && signinForm.returnURL.startsWith("/")) {
            return signinForm.returnURL + "?redirect=true";
        }
        return "/";
    }

    @Execute(validator = false)
    public String signout() {
        ExternalContextUtil.invalidateSession();
        return "/";
    }
}
