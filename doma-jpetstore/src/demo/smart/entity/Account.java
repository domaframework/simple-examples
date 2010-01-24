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
package demo.smart.entity;

import java.io.Serializable;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String username;

    public String password;

    public String email;

    public String firstName;

    public String lastName;

    public String status;

    @Column(name = "ADDR1")
    public String address1;

    @Column(name = "ADDR2")
    public String address2;

    public String city;

    public String state;

    public String zip;

    public String country;

    public String phone;

    @Column(name = "FAVCATEGORY")
    public String favouriteCategoryId;

    @Column(name = "LANGPREF")
    public String languagePreference;

    @Column(name = "MYLISTOPT")
    public boolean listOption;

    @Column(name = "BANNEROPT")
    public boolean bannerOption;

    public String bannerName;

}
