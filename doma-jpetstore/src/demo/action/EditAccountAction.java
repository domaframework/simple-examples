package demo.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import demo.entity.Account;
import demo.form.EditAccountForm;
import demo.service.AccountService;
import demo.session.User;

public class EditAccountAction {

    protected AccountService accountService = new AccountService();

    @ActionForm
    @Resource
    protected EditAccountForm editAccountForm;

    // out
    public List<String> languageList = Constants.LANGUAGE_LIST;

    // out
    public List<String> categoryList = Constants.CATEGORY_LIST;

    // out
    public Account account;

    @Execute(validator = true, input = "newAccountForm.jsp")
    public String newAccount() {
        account = new Account();
        Beans.copy(editAccountForm, account).execute();
        try {
            accountService.insertAccount(account);
            User user = User.get();
            user.setUsername(account.username);
            user.setFirstName(account.firstName);
            user.setAuthenticated(true);
            return "/";
        } catch (Exception e) {
            throw new RuntimeException(
                    "There was a problem creating your Account Information.  Cause: "
                            + e, e);
        }
    }

    @Execute(validator = false)
    public String editAccountForm() {
        User signin = User.get();
        try {
            account = accountService.getAccount(signin.getUsername());
            Beans.copy(account, editAccountForm).execute();
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
        Beans.copy(editAccountForm, account).execute();
        try {
            accountService.updateAccount(account);
            User user = User.get();
            user.setUsername(account.username);
            user.setFirstName(account.firstName);
            user.setAuthenticated(true);
            return "/?redirect=true";
        } catch (Exception e) {
            throw new RuntimeException(
                    "There was a problem updating your Account Information. Cause: "
                            + e, e);
        }
    }

}
