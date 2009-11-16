<%@ include file="../common/header.jsp" %>

<div id="Catalog">

  <s:form>

    <h3>User Information</h3>

    <table>
      <tr>
        <td>User ID:</td><td>${f:h(username)}<input type="hidden" name="username" value="${f:h(username)}"/></td>
      </tr><tr>
      <td>New password:</td><td><input type="password" name="password"/></td>
    </tr><tr>
      <td>Repeat password:</td><td><input type="password" name="repeatedPassword"/></td>
    </tr>
    </table>
    <%@ include file="includeAccountFields.jsp" %>

    <input type="submit" name="editAccount" value="Save Account Information"/>

  </s:form>

  <s:link href="/order/listOrders">My Orders</s:link>

</div>

<%@ include file="../common/footer.jsp" %>


