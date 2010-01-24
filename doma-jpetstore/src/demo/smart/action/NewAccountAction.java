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

import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import demo.smart.entity.Account;
import demo.smart.form.NewAccountForm;
import demo.smart.service.AccountService;
import demo.smart.util.TokenUtil;

public class NewAccountAction {

    @Resource
    protected AccountService accountService;

    @ActionForm
    @Resource
    protected NewAccountForm newAccountForm;

    // out
    public List<String> languageList = Constants.LANGUAGE_LIST;

    // out
    public List<String> categoryList = Constants.CATEGORY_LIST;

    // out
    public Account account;

    @Execute(validator = false)
    public String newAccountForm() {

        TokenUtil.save();

        return "newAccountForm.jsp";
    }

    @Execute(validator = true, validate = "validateToken", input = "newAccountForm.jsp")
    public String newAccount() {
        account = new Account();
        Beans.copy(newAccountForm, account).execute();
        try {
            accountService.insertAccount(account);
            return "/signin/signinForm?redirect=true";
        } catch (Exception e) {
            throw new RuntimeException(
                    "There was a problem creating your Account Information.  Cause: "
                            + e, e);
        }
    }

    public ActionMessages validateToken() {
        return TokenUtil.validate();
    }

}
