<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<link rel="stylesheet" type="text/css" href="${f:url('/css/jpetstore.css')}"/>

<head>
  <meta name="generator"
        content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org"/>
  <title>JPetStore Demo</title>
  <meta content="text/html; charset=windows-1252" http-equiv="Content-Type"/>
  <meta http-equiv="Cache-Control" content="max-age=0"/>
  <meta http-equiv="Cache-Control" content="no-cache"/>
  <meta http-equiv="expires" content="0"/>
  <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT"/>
  <meta http-equiv="Pragma" content="no-cache"/>
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
        <c:when test="${empty SIGNIN || !SIGNIN.authenticated}">
          <html:link page="/account/signinForm">Sign In</html:link>
        </c:when>
        <c:otherwise>
          <html:link page="/account/signout">Sign Out</html:link>
          <img align="middle" src="${f:url('/images/separator.gif')}"/>
          <html:link page="/account/editAccountForm">My Account</html:link>
        </c:otherwise>
      </c:choose>
      <img align="middle" src="${f:url('/images/separator.gif')}"/>
      <html:link href="${f:url('/help.html')}">?</html:link>
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
