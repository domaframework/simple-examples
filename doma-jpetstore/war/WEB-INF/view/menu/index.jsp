<%@ include file="../common/header.jsp" %>

<div id="Welcome">
  <div id="WelcomeContent">
    <c:if test="${not empty USER && USER.authenticated}">
      Welcome ${f:h(USER.firstName)}!<br/>
    </c:if>
  </div>
</div>

<div id="Main">
  <div id="Sidebar">
    <div id="SidebarContent">
      <s:link href="/catalog/viewCategory/FISH">
        <img src="${f:url('/images/fish_icon.gif')}"/>
      </s:link>
      <br/>
      Saltwater, Freshwater
      <br/>
      <s:link href="/catalog/viewCategory/DOGS">
        <img src="${f:url('/images/dogs_icon.gif')}"/></s:link>
      <br/>
      Various Breeds
      <br/>
      <s:link href="/catalog/viewCategory/CATS">
        <img src="${f:url('/images/cats_icon.gif')}"/></s:link>
      <br/>
      Various Breeds, Exotic Varieties
      <br/>
      <s:link href="/catalog/viewCategory/REPTILES">
        <img src="${f:url('/images/reptiles_icon.gif')}"/></s:link>
      <br/>
      Lizards, Turtles, Snakes
      <br/>
      <s:link href="/catalog/viewCategory/BIRDS">
        <img src="${f:url('/images/birds_icon.gif')}"/></s:link>
      <br/>Exotic Varieties
    </div>
  </div>

  <div id="MainImage">
    <div id="MainImageContent">
      <map name="estoremap"><area alt="Birds" coords="72,2,280,250" href="${f:url('/catalog/viewCategory/BIRDS')}"
                                  shape="RECT"/>
        <area alt="Fish" coords="2,180,72,250" href="${f:url('/catalog/viewCategory/FISH')}" shape="RECT"/>
        <area alt="Dogs" coords="60,250,130,320" href="${f:url('/catalog/viewCategory/DOGS')}" shape="RECT"/>
        <area alt="Reptiles" coords="140,270,210,340" href="${f:url('/catalog/viewCategory/REPTILES')}"
              shape="RECT"/>
        <area alt="Cats" coords="225,240,295,310" href="${f:url('/catalog/viewCategory/CATS')}" shape="RECT"/>
        <area alt="Birds" coords="280,180,350,250" href="${f:url('/catalog/viewCategory/BIRDS')}" shape="RECT"/>
      </map>
      <img height="355" src="${f:url('/images/splash.gif')}" align="center" usemap="#estoremap" width="350"/>
    </div>
  </div>

  <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/footer.jsp" %>

