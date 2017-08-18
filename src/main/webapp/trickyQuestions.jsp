<%@include file="taglib.jsp"%>
<c:set var="title" value="Tricky Questions" />
<%@include file="head.jsp"%>
<!--  Holliann Niesen
      Mathtastic Games Homepage
      06/29/17 -->
<html lang="en">

<body>
<div class="container">

  <header class="jumbotron">
    <h1>Apple Addition Tricky Questions</h1>
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
  <div class="col-xs-offset-2 col-xs-8 center">
    <h1 id="resultsHeader">Tricky Question History</h1>
    <c:choose>
      <c:when test="${trickyQuestionList!=null}">
        <table id="historyTable" width="100%">
          <thead>
          <th class="center">Question</th>
          <th class="center">Answer</th>
          <th class="center">Attempts</th>
          <th class="center">Date Asked</th>
          </thead>
          <tbody>
          <c:forEach var="trickyQuestion" items="${trickyQuestionList}">
            <tr>
              <td>${trickyQuestion.question.toString()}</td>
              <td>${trickyQuestion.question.answer}</td>
              <td>${trickyQuestion.attempts}</td>
              <td>${trickyQuestion.dateAsked}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </c:when>
      <c:otherwise>
        <p>Nothing to show here! Great job!</p>
      </c:otherwise>
    </c:choose>
  </div>

  <div class="col-xs-2"></div>

</div>
</body>
</html>