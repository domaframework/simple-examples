<%@ include file="../common/header.jsp" %>

<div id="BackLink">

  <s:link href="/">Return to Main Menu</s:link>

</div>

<div id="Catalog">

  <h2>${f:h(category.name)}</h2>

  <table>
    <tr><th>Product ID</th>  <th>Name</th></tr>
    <c:forEach var="product" varStatus="s" items="${productList}">
      <tr>
        <td><s:link href="viewProduct/${product.productId}">${f:h(product.productId)}</s:link></td>
        <td>${f:h(product.name)}</td>
      </tr>
    </c:forEach>
  </table>

</div>

<%@ include file="../common/footer.jsp" %>