<h3>Account Information</h3>

<table>
  <tr>
    <td>First name:</td><td><html:text property="firstName"/></td>
  </tr><tr>
    <td>Last name:</td><td><html:text property="lastName"/></td>
  </tr><tr>
    <td>Email:</td><td><html:text size="40" property="email"/></td>
  </tr><tr>
    <td>Phone:</td><td><html:text property="phone"/></td>
  </tr><tr>
    <td>Address 1:</td><td><html:text size="40" property="address1"/></td>
  </tr><tr>
    <td>Address 2:</td><td><html:text size="40" property="address2"/></td>
  </tr><tr>
    <td>City:</td><td><html:text property="city"/></td>
  </tr><tr>
    <td>State:</td><td><html:text size="4" property="state"/></td>
  </tr><tr>
    <td>Zip:</td><td><html:text size="10" property="zip"/></td>
  </tr><tr>
    <td>Country:</td><td><html:text size="15" property="country"/></td>
  </tr>
</table>

<h3>Profile Information</h3>

<table>
  <tr>
    <td>Language Preference:</td><td>
    <html:select property="languagePreference">
      <html:options name="languageList"/>
    </html:select></td>
  </tr><tr>
  <td>Favourite Category:</td><td>
  <html:select property="favouriteCategoryId">
    <html:options name="categoryList"/>
  </html:select></td>
  </tr><tr>
    <td>Enable MyList</td><td><html:checkbox property="listOption"/></td>
  </tr><tr>
    <td>Enable MyBanner</td><td><html:checkbox property="bannerOption"/></td>
  </tr>
</table>
