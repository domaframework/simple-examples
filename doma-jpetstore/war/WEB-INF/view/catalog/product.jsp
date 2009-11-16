<%@ include file="../common/header.jsp" %>

<div id="BackLink">

  <s:link href="viewCategory/${product.categoryId}">Return to ${f:h(product.categoryId)}</s:link>

</div>

<div id="Catalog">

  <h2>${f:h(product.name)}</h2>

  <table>
    <tr><th>Item ID</th>  <th>Product ID</th>  <th>Description</th>  <th>List
      Price</th>  <th>&nbsp;</th></tr>
    <c:forEach var="item" varStatus="s" items="${itemList}">
      <tr>
        <td>
          <s:link href="viewItem/${item.itemId}">${f:h(item.itemId)}</s:link></td>
        <td>${f:h(item.productId)}</td>
        <td>
          ${f:h(item.attribute1)}
          ${f:h(item.attribute2)}
          ${f:h(item.attribute3)}
          ${f:h(item.attribute4)}
          ${f:h(item.attribute5)}
          ${f:h(item.productName)}
        </td>
        <td><fmt:formatNumber value="${item.listPrice}" pattern="$#,##0.00"/>
        <td><s:link styleClass="Button" href="/cart/addItemToCart/${item.itemId}">Add to Cart</s:link></td>
      </tr>
    </c:forEach>
  </table>

</div>

<%@ include file="../common/footer.jsp" %>
