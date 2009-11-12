<%@ include file="../common/IncludeTop.jsp" %>

<bean:define id="productList" name="catalogBean" property="productList"/>

<div id="BackLink">

  <html:link page="/shop/index.shtml">Return to Main Menu</html:link>

</div>

<div id="Catalog">

  <table>
    <tr><th>&nbsp;</th>  <th>Product ID</th>  <th>Name</th></tr>
    <logic:iterate id="product" name="productList">
      <tr>
        <td><html:link paramId="productId" paramName="product" paramProperty="productId" page="/shop/viewProduct.shtml">
          <bean:write filter="false" name="product" property="description"/></html:link></td>
        <td><b><html:link paramId="productId" paramName="product" paramProperty="productId"
                          page="/shop/viewProduct.shtml"><font color="BLACK"><bean:write name="product"
                                                                                         property="productId"/></font>
        </html:link></b></td>
        <td><bean:write name="product" property="name"/></td>
      </tr>
    </logic:iterate>
  </table>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>




