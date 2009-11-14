<%@ include file="../common/IncludeTop.jsp" %>

<h2>My Orders</h2>

<table>
  <tr><th>Order ID</th>  <th>Date</th>  <th>Total Price</th></tr>

  <logic:iterate id="order" name="orderBean" property="orderList">
    <tr>
      <td><html:link paramId="orderId" paramName="order" paramProperty="orderId" page="/shop/viewOrder.shtml">
        <bean:write name="order" property="orderId"/></html:link></td>
      <td><bean:write name="order" property="orderDate" format="yyyy/MM/dd hh:mm:ss"/></td>
      <td><bean:write name="order" property="totalPrice" format="$#,##0.00"/></td>
    </tr>
  </logic:iterate>
</table>

<%@ include file="../common/IncludeBottom.jsp" %>


