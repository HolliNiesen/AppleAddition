<%@include file="taglib.jsp"%>
<c:set var="title" value="Apple Addition" />
<%@include file="head.jsp"%>
<!--  Holliann Niesen
Mathtastic Games Apple Addition
06/20/17 -->
<html lang="en">

<body>
<div class="container">

  <header class="jumbotron">
    <h1>Apple Addition!</h1>
  </header>

  <nav>
    <div class="navbar navbar-default">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.jsp">MG!</a>
      </div>

      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
          <li><a href="appleAddition.jsp">Apple Addition</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <c:set var="user" value="${sessionScope.user}" />
          <c:choose>
            <c:when test="${user == null}">
              <li><a href="signUp.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
              <li><a href="signIn.jsp"><span class="glyphicon glyphicon-log-in"></span> Sign In</a></li>
            </c:when>
            <c:otherwise>
              <li><a href="myAccount.jsp"><span class="glyphicon glyphicon-user"></span> ${user.firstName}</a></li>
              <li><a href="signOutUser"><span class="glyphicon glyphicon-log-out"></span> Sign Out</a></li>
            </c:otherwise>
          </c:choose>
        </ul>
      </div>
    </div>
  </nav>

  <div id="gameBox" class="col-md-offset-2 col-md-8">
    <c:set var="imagesOn" value="${sessionScope.imagesOn}" />
    <div class="gameCol col-xs-3">
      ${leftNumber}
      <c:if test="${imagesOn}">
        <img class="gameImage" src="images/${leftImage}" />
      </c:if>
    </div>

    <div class="gameCol col-xs-1">
      <span>+</span>
    </div>

    <div class="gameCol col-xs-3">
      ${rightNumber}
      <c:if test="${imagesOn}">
        <img class="gameImage" src="images/${rightImage}" />
      </c:if>
    </div>

    <div class="gameCol col-xs-1">
      <span>=</span>
    </div>

    <div class="gameCol col-xs-3">
      ${answer}
      <c:if test="${imagesOn}">
        <img class="gameImage" src="images/${answerImage}" />
      </c:if>

      <form action="askQuestion">
        <div class="form-group">
          <input style="display:none;" type="checkbox" name="questions" value="items='${questions}'" checked>
          <input class="btn" type="submit" name="nextQuestion" value="Next Question">
        </div>
      </form>
    </div>
  </div>

</div>
</body>
</html>