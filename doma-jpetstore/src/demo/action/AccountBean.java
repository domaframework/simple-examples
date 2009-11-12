package demo.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.struts.beanaction.BeanActionException;

import com.ibatis.common.util.PaginatedList;

import demo.entity.Account;
import demo.service.AccountService;
import demo.service.CatalogService;

public class AccountBean extends AbstractBean implements Serializable {

    private static final List LANGUAGE_LIST;
    private static final List CATEGORY_LIST;

    private Account account;
    private String repeatedPassword;
    private String pageDirection;
    private String validation;
    private PaginatedList myList;
    private boolean authenticated;

    static {
        List langList = new ArrayList();
        langList.add("english");
        langList.add("japanese");
        LANGUAGE_LIST = Collections.unmodifiableList(langList);

        List catList = new ArrayList();
        catList.add("FISH");
        catList.add("DOGS");
        catList.add("REPTILES");
        catList.add("CATS");
        catList.add("BIRDS");
        CATEGORY_LIST = Collections.unmodifiableList(catList);
    }

    public AccountBean() {
        this(new AccountService(), new CatalogService());
    }

    public AccountBean(AccountService accountService,
            CatalogService catalogService) {
        account = new Account();
    }

    public String getUsername() {
        return account.getUsername();
    }

    public void setUsername(String username) {
        account.setUsername(username);
    }

    public String getPassword() {
        return account.getPassword();
    }

    public void setPassword(String password) {
        account.setPassword(password);
    }

    public PaginatedList getMyList() {
        return myList;
    }

    public void setMyList(PaginatedList myList) {
        this.myList = myList;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List getLanguages() {
        return LANGUAGE_LIST;
    }

    public List getCategories() {
        return CATEGORY_LIST;
    }

    public String getPageDirection() {
        return pageDirection;
    }

    public void setPageDirection(String pageDirection) {
        this.pageDirection = pageDirection;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String newAccount() {
        try {
            getAccountService().insertAccount(account);
            account = getAccountService().getAccount(account.getUsername());
            // TODO
            // myList =
            // catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            authenticated = true;
            repeatedPassword = null;
            return SUCCESS;
        } catch (Exception e) {
            throw new BeanActionException(
                    "There was a problem creating your Account Information.  Cause: "
                            + e, e);
        }
    }

    public String editAccountForm() {
        try {
            account = getAccountService().getAccount(account.getUsername());
            return SUCCESS;
        } catch (Exception e) {
            throw new BeanActionException(
                    "There was a problem retrieving your Account Information. Cause: "
                            + e, e);
        }
    }

    public String editAccount() {
        try {
            getAccountService().updateAccount(account);
            account = getAccountService().getAccount(account.getUsername());
            // TODO
            // myList = catalogService.getProductListByCategory(account
            // .getFavouriteCategoryId());
            return SUCCESS;
        } catch (Exception e) {
            throw new BeanActionException(
                    "There was a problem updating your Account Information. Cause: "
                            + e, e);
        }
    }

    public String switchMyListPage() {
        if ("next".equals(pageDirection)) {
            myList.nextPage();
        } else if ("previous".equals(pageDirection)) {
            myList.previousPage();
        }
        return SUCCESS;
    }

    public String signon() {

        account = getAccountService().getAccount(account.getUsername(),
                account.getPassword());

        if (account == null || account == null) {
            String value = "Invalid username or password.  Signon failed.";
            setMessage(value);
            clear();
            return FAILURE;
        } else {
            account.setPassword(null);

            // TODO
            // myList = catalogService.getProductListByCategory(account
            // .getFavouriteCategoryId());

            authenticated = true;

            return SUCCESS;
        }
    }

    public String signoff() {
        // ActionContext.getActionContext().getRequest().getSession().invalidate();
        clear();
        return SUCCESS;
    }

    public boolean isAuthenticated() {
        return authenticated && account != null
                && account.getUsername() != null;
    }

    @Override
    public void reset() {
        if (account != null) {
            account.setBannerOption(false);
            account.setListOption(false);
        }
    }

    public void clear() {
        account = new Account();
        repeatedPassword = null;
        pageDirection = null;
        myList = null;
        authenticated = false;
    }

    /**
     * @return the accountService
     */
    private AccountService getAccountService() {
        return new AccountService();
    }

    /**
     * @return the catalogService
     */
    private CatalogService getCatalogService() {
        return new CatalogService();
    }

}
