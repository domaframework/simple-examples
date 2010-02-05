<%@ include file="../common/header.jsp" %>

<div id="BackLink">

    <s:link href="/">Return to Main Menu</s:link>

</div>

<div id="Catalog">

  <table>
    <tr><th>&nbsp;</th>  <th>Product ID</th>  <th>Name</th></tr>
    <c:forEach var="product" varStatus="s" items="${productList}">
      <tr>
        <td><s:link href="/catalog/viewProduct/${product.productId}">${product.description}</s:link></td>
        <td><b><s:link href="/catalog/viewProduct/${product.productId}">
          <font color="BLACK">${f:h(product.productId)}</font>
        </s:link></b></td>
        <td>${f:h(product.name)}</td>
      </tr>
    </c:forEach>
  </table>

</div>

<%@ include file="../common/footer.jsp" %>
