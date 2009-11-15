<%@ include file="../common/IncludeTop.jsp" %>

<div id="Catalog">

  <s:form>

    <table>
      <tr><th colspan=2>
        Shipping Address
      </th></tr>

      <tr><td>
        First name:</td><td><html:text property="shipToFirstName"/>
      </td></tr>
      <tr><td>
        Last name:</td><td><html:text property="shipToLastName"/>
      </td></tr>
      <tr><td>
        Address 1:</td><td><html:text size="40" property="shipAddress1"/>
      </td></tr>
      <tr><td>
        Address 2:</td><td><html:text size="40" property="shipAddress2"/>
      </td></tr>
      <tr><td>
        City: </td><td><html:text property="shipCity"/>
      </td></tr>
      <tr><td>
        State:</td><td><html:text size="4" property="shipState"/>
      </td></tr>
      <tr><td>
        Zip:</td><td><html:text size="10" property="shipZip"/>
      </td></tr>
      <tr><td>
        Country: </td><td><html:text size="15" property="shipCountry"/>
      </td></tr>

    </table>

    <input type="submit" name="newOrderWithShippingAddress" value="Continue">

  </s:form>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>