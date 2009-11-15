<%@ include file="../common/IncludeTop.jsp" %>

<div id="BackLink">
  <s:link href="/">Return to Main Menu</s:link>
</div>

<div id="Catalog">

  <div id="Cart">

    <h2>Shopping Cart</h2>
    <s:form>
      <table>
        <tr>
          <th><b>Item ID</b></th>  <th><b>Product ID</b></th>  <th><b>Description</b></th> <th><b>In Stock?</b></th>
          <th><b>Quantity</b></th>  <th><b>List Price</b></th> <th><b>Total Cost</b></th>  <th>&nbsp;</th>
        </tr>

        <c:if test="${cart.numberOfItems == 0}">
          <tr><td colspan="8"><b>Your cart is empty.</b></td></tr>
        </c:if>

        <c:forEach var="cartItem" varStatus="s" items="${cart.cartItemList}">
          <tr>
            <td>
              <s:link href="/catalog/viewItem/${cartItem.item.itemId}">${f:h(cartItem.item.itemId)}</s:link>
            </td>
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
              <input type="text" size="3" name="${f:h(cartItem.item.itemId)}"
                     value="${f:h(cartItem.quantity)}"/>
            </td>
            <td><fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00"/></td>
            <td><fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00"/></td>
            <td>
              <s:link href="removeItemFromCart?itemId=${cartItem.item.itemId}" styleClass="Button">Remove</s:link>
            </td>
          </tr>
          </c:forEach>
        <tr>
          <td colspan="7">
            Sub Total: <fmt:formatNumber value="${cart.subTotal}" pattern="$#,##0.00"/>
            <input type="submit" name="updateCartQuantities" value="Update Cart"/>
          </td>
          <td>&nbsp;</td>
        </tr>
      </table>
    </s:form>

    <c:if test="${cart.numberOfItems != 0}">
      <s:link styleClass="Button" href="checkout">Proceed to Checkout</s:link>
    </c:if>
        
  </div>

  <div id="Separator">&nbsp;</div>

</div>


<%@ include file="../common/IncludeBottom.jsp" %>



