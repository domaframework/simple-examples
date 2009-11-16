<%@ include file="../common/header.jsp" %>

<div id="BackLink">

  <s:link href="viewProduct/${item.productId}">Return to ${f:h(item.productId)}</s:link>

</div>

<div id="Catalog">

  <table>
    <tr>
      <td>
        ${item.productDescription}
      </td>
    </tr>
    <tr>
      <td>
        <b>${f:h(item.itemId)}</b>
      </td>
    </tr><tr>
    <td>
      <b><font size="4">
          ${f:h(item.attribute1)}
          ${f:h(item.attribute2)}
          ${f:h(item.attribute3)}
          ${f:h(item.attribute4)}
          ${f:h(item.attribute5)}
          ${f:h(item.productName)}
      </font></b>
    </td></tr>
    <tr><td>
      ${f:h(item.productName)}
    </td></tr>
    <tr><td>
      <c:if test="${item.quantity < 0}">
        Back ordered.
      </c:if>
      <c:if test="${item.quantity > 1}">
        ${item.quantity} in stock.
      </c:if>
    </td></tr>
    <tr><td>
      <fmt:formatNumber value="${item.listPrice}" pattern="$#,##0.00"/>
    </td></tr>

    <tr><td>
      <s:link styleClass="Button" href="/cart/addItemToCart/${item.itemId}">Add to Cart</s:link>
    </td></tr>
  </table>

</div>

<%@ include file="../common/footer.jsp" %>