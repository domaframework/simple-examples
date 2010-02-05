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
package demo.smart.session;

import java.io.Serializable;

import demo.smart.util.ExternalContextUtil;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private boolean authorized;

    private String firstName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuthorized() {
        return authorized && username != null;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static User get() {
        User user = (User) ExternalContextUtil.getSession().getAttribute(
                SessionKeys.USER);
        if (user == null) {
            return new User();
        }
        return user;
    }

    public static void put(User signin) {
        ExternalContextUtil.getSession().setAttribute(SessionKeys.USER, signin);
    }

    public static void clear() {
        ExternalContextUtil.getSession().removeAttribute(SessionKeys.USER);
    }

}
