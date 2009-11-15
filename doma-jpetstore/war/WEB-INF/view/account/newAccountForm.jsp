<%@ include file="../common/IncludeTop.jsp" %>

<div id="Catalog">

  <s:form>

    <h3>User Information</h3>

    <table>
      <tr>
        <td>User ID:</td><td><html:text property="username"/></td>
      </tr><tr>
      <td>New password:</td><td><input type="password" name="password"/></td>
    </tr><tr>
      <td>Repeat password:</td><td><input type="password" name="repeatedPassword"/></td>
    </tr>
    </table>

    <%@ include file="includeAccountFields.jsp" %>

    <input type="submit" name="newAccount" value="Create Account"/>

  </s:form>

</div>

<%@ include file="../common/IncludeBottom.jsp" %>