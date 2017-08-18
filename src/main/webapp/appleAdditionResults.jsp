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

  <div id="gameBox" class="col-md-offset-2 col-md-8 center">
    <h1 id="resultsHeader">Tricky Questions</h1>
    <c:choose>
      <c:when test="${trickyQuestions!=null}">
        <table id="resultsTable" width="100%">
          <thead>
            <th class="center">Question</th>
            <th class="center">Answer</th>
            <th class="center">Attempts</th>
          </thead>
          <tbody>
            <c:forEach var="trickyQuestion" items="${trickyQuestions}">
              <tr>
                <td>${trickyQuestion.getKey().toString()}</td>
                <td>${trickyQuestion.getKey().answer}</td>
                <td>${trickyQuestion.getValue()}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:when>
      <c:otherwise>
        <p>No tricky questions! Great job!</p>
      </c:otherwise>
    </c:choose>
    <form action="appleAddition.jsp">
      <div class="form-group">
        <input class="btn" type="submit" name="playAgain" value="Play Again">
      </div>
    </form>
  </div>

</div>
</body>
</html>