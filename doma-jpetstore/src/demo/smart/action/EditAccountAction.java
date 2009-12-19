package demo.smart.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import demo.action.Authorize;
import demo.smart.entity.Account;
import demo.smart.form.EditAccountForm;
import demo.smart.service.AccountService;
import demo.smart.session.User;
import demo.smart.util.TokenUtil;

@Authorize
public class EditAccountAction {

    @Resource
    protected AccountService accountService;

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
            user.setAuthorized(true);
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

            TokenUtil.save();

            return "editAccountForm.jsp";
        } catch (Exception e) {
            throw new RuntimeException(
                    "There was a problem retrieving your Account Information. Cause: "
                            + e, e);
        }
    }

    @Execute(validator = true, validate = "validateToken", input = "editAccountForm.jsp")
    public String editAccount() {
        account = new Account();
        Beans.copy(editAccountForm, account).execute();
        try {
            accountService.updateAccount(account);
            User user = User.get();
            user.setUsername(account.username);
            user.setFirstName(account.firstName);
            user.setAuthorized(true);
            return "/?redirect=true";
        } catch (Exception e) {
            throw new RuntimeException(
                    "There was a problem updating your Account Information. Cause: "
                            + e, e);
        }
    }

    public ActionMessages validateToken() {
        return TokenUtil.validate();
    }

}
