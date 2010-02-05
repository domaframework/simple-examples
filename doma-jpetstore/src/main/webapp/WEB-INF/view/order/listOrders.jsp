<%@ include file="../common/header.jsp" %>

<h2>My Orders</h2>

<table>
  <tr><th>Order ID</th>  <th>Date</th>  <th>Total Price</th></tr>

  <c:forEach var="order" varStatus="s" items="${orderList}">
    <tr>
      <td><s:link href="viewOrder/${order.orderId}">
        ${f:h(order.orderId)}</s:link></td>
      <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss"/></td>
      <td><fmt:formatNumber value="${order.totalPrice.value}" pattern="$#,##0.00"/></td>
    </tr>
  </c:forEach>
</table>

<%@ include file="../common/footer.jsp" %>


