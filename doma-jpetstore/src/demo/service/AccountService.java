package demo.service;

import demo.dao.AccountDao;
import demo.dao.impl.AccountDaoImpl;
import demo.entity.Account;

public class AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    public Account getAccount(String username) {
        return accountDao.getAccountByUsername(username);
    }

    public Account getAccount(String username, String password) {
        return accountDao.getAccountByUsernameAndPassword(username, password);
    }

    public void insertAccount(Account account) {
        accountDao.insertAccount(account);
        accountDao.insertProfile(account);
        accountDao.insertSignon(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
        accountDao.updateProfile(account);
        if (account.password != null && account.password.length() > 0) {
            accountDao.updateSignon(account);
        }
    }

}
