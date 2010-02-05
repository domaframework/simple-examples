<%@ include file="../common/header.jsp" %>


<div id="BackLink">
  <s:link href="/">Return to Main Menu</s:link>
</div>

<div id="Catalog">

  Please confirm the information below and then press continue...

  <table>
    <tr><th align="center" colspan="2">
      <font size="4"><b>Order</b></font>
      <br/><font size="3"><b><fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss"/></b></font>
    </th></tr>

    <tr><th colspan="2">
      Billing Address
    </th></tr>
    <tr><td>
      First name:</td><td>${f:h(order.billToFirstName)}
    </td></tr>
    <tr><td>
      Last name:</td><td>${f:h(order.billToLastName)}
    </td></tr>
    <tr><td>
      Address 1:</td><td>${f:h(order.billAddress1)}
    </td></tr>
    <tr><td>
      Address 2:</td><td>${f:h(order.billAddress2)}
    </td></tr>
    <tr><td>
      City: </td><td>${f:h(order.billCity)}
    </td></tr>
    <tr><td>
      State:</td><td>${f:h(order.billState)}
    </td></tr>
    <tr><td>
      Zip:</td><td>${f:h(order.billZip)}
    </td></tr>
    <tr><td>
      Country: </td><td>${f:h(order.billCountry)}
    </td></tr>
    <tr><th colspan="2">
      Shipping Address
    </th></tr><tr><td>
    First name:</td><td>${f:h(order.shipToFirstName)}
  </td></tr>
    <tr><td>
      Last name:</td><td>${f:h(order.shipToLastName)}
    </td></tr>
    <tr><td>
      Address 1:</td><td>${f:h(order.shipAddress1)}
    </td></tr>
    <tr><td>
      Address 2:</td><td>${f:h(order.shipAddress2)}
    </td></tr>
    <tr><td>
      City: </td><td>${f:h(order.shipCity)}
    </td></tr>
    <tr><td>
      State:</td><td>${f:h(order.shipState)}
    </td></tr>
    <tr><td>
      Zip:</td><td>${f:h(order.shipZip)}
    </td></tr>
    <tr><td>
      Country: </td><td>${f:h(order.shipCountry)}
    </td></tr>

  </table>

  <s:form>
    <input type="submit" name="confirm" value="Confirm">
  </s:form>

</div>

<%@ include file="../common/footer.jsp" %>





