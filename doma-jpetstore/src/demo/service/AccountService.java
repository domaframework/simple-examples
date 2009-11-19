package demo.service;

import org.seasar.framework.beans.util.Beans;

import demo.dao.AccountDao;
import demo.dao.ProfileDao;
import demo.dao.SignonDao;
import demo.dao.impl.AccountDaoImpl;
import demo.dao.impl.ProfileDaoImpl;
import demo.dao.impl.SignonDaoImpl;
import demo.entity.Account;
import demo.entity.Profile;
import demo.entity.Signon;

public class AccountService {

    protected AccountDao accountDao = new AccountDaoImpl();

    protected ProfileDao profileDao = new ProfileDaoImpl();

    protected SignonDao signonDao = new SignonDaoImpl();

    public Account getAccount(String username) {
        return accountDao.getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        return accountDao.getAccountByUsernameAndPassword(username, password);
    }

    public void insertAccount(Account account) {
        accountDao.insertAccount(account);

        Profile profile = new Profile();
        Beans.copy(account, profile).execute();
        profileDao.insertProfile(profile);

        Signon signon = new Signon();
        Beans.copy(account, signon).execute();
        signonDao.insertSignon(signon);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);

        Profile profile = new Profile();
        Beans.copy(account, profile).execute();
        profileDao.updateProfile(profile);

        Signon signon = new Signon();
        Beans.copy(account, signon).execute();
        if (signon.password != null && signon.password.length() > 0) {
            signonDao.updateSignon(signon);
        }
    }

}
