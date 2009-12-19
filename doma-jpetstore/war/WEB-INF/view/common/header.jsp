<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>JPetStore Demo</title>
  <link rel="stylesheet" type="text/css" href="${f:url('/css/jpetstore.css')}"/>
</head>

<body>

<div id="Header">
  
  <div id="Logo">
    <div id="LogoContent">
      <s:link href="/"><img src="${f:url('/images/logo-topbar.gif')}"/></s:link>
    </div>
  </div>

  <div id="Menu">
    <div id="MenuContent">
      <s:link href="/cart/viewCart"><img align="middle" name="img_cart" src="${f:url('/images/cart.gif')}"/></s:link>
      <img align="middle" src="${f:url('/images/separator.gif')}"/>
      <c:choose>
        <c:when test="${empty USER || !USER.authorized}">
          <html:link page="/signin/signinForm">Sign In</html:link>
        </c:when>
        <c:otherwise>
          <html:link page="/signin/signout">Sign Out</html:link>
          <img align="middle" src="${f:url('/images/separator.gif')}"/>
          <html:link page="/editAccount/editAccountForm">My Account</html:link>
        </c:otherwise>
      </c:choose>
    </div>
  </div>

  <div id="Search">
    <div id="SearchContent">
      <s:form method="post" action="/searchProducts/">
        <input name="keyword" size="14"/>&nbsp;<input type="submit" name="SearchButton"
        value="Search"/>
      </s:form>
    </div>
  </div>

  <div id="QuickLinks">
    <s:link href="/catalog/viewCategory/FISH">
      <img src="${f:url('/images/sm_fish.gif')}"/></s:link>
    <img src="${f:url('/images/separator.gif')}"/>
    <s:link href="/catalog/viewCategory/DOGS">
      <img src="${f:url('/images/sm_dogs.gif')}"/></s:link>
    <img src="${f:url('/images/separator.gif')}"/>
    <s:link href="/catalog/viewCategory/REPTILES">
      <img src="${f:url('/images/sm_reptiles.gif')}"/></s:link>
    <img src="${f:url('/images/separator.gif')}"/>
    <s:link href="/catalog/viewCategory/CATS">
      <img src="${f:url('/images/sm_cats.gif')}"/></s:link>
    <img src="${f:url('/images/separator.gif')}"/>
     <s:link href="/catalog/viewCategory/BIRDS">
      <img src="${f:url('/images/sm_birds.gif')}"/></s:link>
  </div>

</div>

<div id="Content">
<html:errors/>
<html:messages id="message" message="true">
  ${message}
</html:messages>
