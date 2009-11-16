<%@ include file="../common/header.jsp" %>

<div id="Catalog">
  <s:form>

    <p>Please enter your username and password.</p>
    <p>
      Username:<input type="text" name="username" value="j2ee"/>
      <br/>
      Password:<input type="password" name="password" value="j2ee"/>
    </p>
    <input type="submit" name="signin" value="Singin"/>

  </s:form>

  Need a username and password?
  <s:link href="newAccountForm">Register Now!</s:link>

</div>

<%@ include file="../common/footer.jsp" %>

