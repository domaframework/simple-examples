<%@ include file="../common/IncludeTop.jsp" %>

<div id="BackLink">
  <s:link href="viewCart">Return to Shopping Cart</s:link>
</div>

<div id="Catalog">

  <table>
    <tr>
      <td>
        <h2>Checkout Summary</h2>

        <table>

          <tr>
            <td><b>Item ID</b></td>  <td><b>Product ID</b></td>  <td><b>Description</b></td> <td><b>In Stock?</b></td>
            <td><b>Quantity</b></td>  <td><b>List Price</b></td> <td><b>Total Cost</b></td>
          </tr>
          
          <c:forEach var="cartItem" varStatus="s" items="${cart.cartItemList}">
            <tr>
              <td><s:link href="/catalog/viewItem/${cartItem.item.itemId}">${f:h(cartItem.item.itemId)}</s:link></td>
              <td>${f:h(cartItem.item.productId)}</td>
              <td>
                ${f:h(cartItem.item.attribute1)}
                ${f:h(cartItem.item.attribute2)}
                ${f:h(cartItem.item.attribute3)}
                ${f:h(cartItem.item.attribute4)}
                ${f:h(cartItem.item.attribute5)}
                ${f:h(cartItem.item.productName)}
              </td>
              <td>${f:h(cartItem.inStock)}</td>
              <td>
                ${f:h(cartItem.quantity)}
              </td>
              <td><fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00"/></td>
              <td><fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00"/></td>
            </tr>
          </c:forEach>
          <tr>
            <td colspan="7">
              Sub Total: <fmt:formatNumber value="${cart.subTotal}" pattern="$#,##0.00"/>
            </td>
          </tr>
        </table>
        <html:link styleClass="Button" page="/order/newOrderForm">Continue</html:link>
      </td>
      <td>
        &nbsp;
      </td>

    </tr>
  </table>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>





