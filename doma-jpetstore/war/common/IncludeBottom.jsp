</div>

<div id="Footer">

  <div id="PoweredBy">
    <html:link page="/shop/index.shtml"><img src="../images/logo-topbar.gif"/></html:link>
  </div>

  <div id="Banner">
    <logic:present name="accountBean" scope="session">
      <logic:equal name="accountBean" property="authenticated" value="true">
        <logic:equal name="accountBean" property="account.bannerOption" value="true">
          <bean:write filter="false" name="accountBean" property="account.bannerName"/>
        </logic:equal>
      </logic:equal>
    </logic:present>
  </div>

</div>

</body>
</html>