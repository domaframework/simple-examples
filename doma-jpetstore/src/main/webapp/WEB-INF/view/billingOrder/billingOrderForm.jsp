<%@ include file="../common/header.jsp" %>

<div id="Catalog">

  <s:form> 

    <table>
      <tr><th colspan=2>
        Payment Details
      </th></tr><tr><td>
      Card Type:</td><td>
      <html:select property="cardType">
        <html:options name="creditCardTypes"/>
      </html:select>
    </td></tr>
      <tr><td>
        Card Number:</td><td><html:text property="creditCard"/>
        * Use a fake number!
      </td></tr>
      <tr><td>
        Expiry Date (MM/YYYY):</td><td><html:text property="expiryDate"/>
      </td></tr>
      <tr><th colspan=2>
        Billing Address
      </th></tr>

      <tr><td>
        First name:</td><td><html:text property="billToFirstName"/>
      </td></tr>
      <tr><td>
        Last name:</td><td><html:text property="billToLastName"/>
      </td></tr>
      <tr><td>
        Address 1:</td><td><html:text size="40" property="billAddress1"/>
      </td></tr>
      <tr><td>
        Address 2:</td><td><html:text size="40" property="billAddress2"/>
      </td></tr>
      <tr><td>
        City: </td><td><html:text property="billCity"/>
      </td></tr>
      <tr><td>
        State:</td><td><html:text size="4" property="billState"/>
      </td></tr>
      <tr><td>
        Zip:</td><td><html:text size="10" property="billZip"/>
      </td></tr>
      <tr><td>
        Country: </td><td><html:text size="15" property="billCountry"/>
      </td></tr>

      <tr><td colspan=2>
        <html:checkbox property="shippingAddressRequired"/> Ship to different address...
      </td></tr>

    </table>

    <input type="submit" name="continueOrder" value="Continue">

  </s:form>

</div>

<%@ include file="../common/footer.jsp" %>