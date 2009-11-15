package demo.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import demo.entity.Account;
import demo.form.AccountForm;
import demo.service.AccountService;
import demo.service.CatalogService;
import demo.session.Cart;
import demo.session.PurchaseOrder;
import demo.session.Signin;
import demo.util.ExternalContextUtil;

public class AccountAction {

    private static final List<String> LANGUAGE_LIST;

    private static final List<String> CATEGORY_LIST;

    static {
        List<String> langList = new ArrayList<String>();
        langList.add("english");
        langList.add("japanese");
        LANGUAGE_LIST = Collections.unmodifiableList(langList);

        List<String> catList = new ArrayList<String>();
        catList.add("FISH");
        catList.add("DOGS");
        catList.add("REPTILES");
        catList.add("CATS");
        catList.add("BIRDS");
        CATEGORY_LIST = Collections.unmodifiableList(catList);
    }

    protected AccountService accountService = new AccountService();

    protected CatalogService catalogService = new CatalogService();

    @ActionForm
    @Resource
    protected AccountForm accountForm;

    // out
    public List<String> languageList = LANGUAGE_LIST;

    // out
    public List<String> categoryList = CATEGORY_LIST;

    // out
    public Account account;

    @Execute(validator = false)
    public String signinForm() {
        return "signinForm.jsp";
    }

    @Execute(validator = false)
    public String newAccountForm() {
        return "newAccountForm.jsp";
    }

    @Execute(validator = true, input = "newAccountForm.jsp")
    public String newAccount() {
        account = new Account();
        Beans.copy(accountForm, account).execute();
        try {
            accountService.insertAccount(account);
            Signin signin = Signin.get();
            signin.setUsername(account.username);
            signin.setFirstName(account.firstName);
            signin.setAuthenticated(true);
            return "/?redirect=true";
        } catch (Exception e) {
            throw new RuntimeException(
                    "There was a problem creating your Account Information.  Cause: "
                            + e, e);
        }
    }

    @Execute(validator = false)
    public String editAccountForm() {
        Signin signin = Signin.get();
        try {
            account = accountService.getAccount(signin.getUsername());
            Beans.copy(account, accountForm).execute();
            return "editAccountForm.jsp";
        } catch (Exception e) {
            throw new RuntimeException(
                    "There was a problem retrieving your Account Information. Cause: "
                            + e, e);
        }
    }

    @Execute(validator = true, input = "editAccountForm.jsp")
    public String editAccount() {
        account = new Account();
        Beans.copy(accountForm, account).execute();
        try {
            accountService.updateAccount(account);
            Signin signin = Signin.get();
            signin.setUsername(account.username);
            signin.setFirstName(account.firstName);
            signin.setAuthenticated(true);
            return "/?redirect=true";
        } catch (Exception e) {
            throw new RuntimeException(
                    "There was a problem updating your Account Information. Cause: "
                            + e, e);
        }
    }

    @Execute(validator = false, input = "signinForm.jsp")
    public String signin() {
        account = accountService.getAccount(accountForm.username,
                accountForm.password);
        if (account == null) {
            throw new ActionMessagesException(
                    "Invalid username or password. Signin failed.", false);
        }

        Cart cart = Cart.get();
        PurchaseOrder purchaseOrder = PurchaseOrder.get();

        ExternalContextUtil.invalidateSession();

        Signin signin = new Signin();
        signin.setUsername(account.username);
        signin.setFirstName(account.firstName);
        signin.setAuthenticated(true);
        Signin.put(signin);
        Cart.put(cart);
        PurchaseOrder.put(purchaseOrder);
        return "/?redirect=true";
    }

    @Execute(validator = false)
    public String signout() {
        ExternalContextUtil.invalidateSession();
        return "/?redirect=true";
    }

}
