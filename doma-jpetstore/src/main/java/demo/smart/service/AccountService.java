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
package demo.smart.service;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;

import demo.smart.dao.AccountDao;
import demo.smart.dao.ProfileDao;
import demo.smart.dao.SignonDao;
import demo.smart.entity.Account;
import demo.smart.entity.Profile;
import demo.smart.entity.Signon;

public class AccountService {

    @Resource
    protected AccountDao accountDao;

    @Resource
    protected ProfileDao profileDao;

    @Resource
    protected SignonDao signonDao;

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
