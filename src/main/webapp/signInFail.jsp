<%@include file="taglib.jsp"%>
<c:set var="title" value="Sign In" />
<%@include file="head.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: Holli
  Date: 8/11/2017
  Time: 9:20 PM
  To change this template use File | Settings | File Templates.
--%>
<html lang="en">

<body>
<div class="container">

  <header class="jumbotron">
    <h1>Mathtastic Games! Sign In</h1>
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

  <form class="form-horizontal" method="post" action="signInUser">

    <div class="form-group">
      <label class="control-label col-xs-4">Email</label>
      <div class="col-xs-4">
        <input class="form-control" type="text" name="email" />
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-xs-4">Password</label>
      <div class="col-xs-4">
        <input class="form-control" type="password" name="password" />
      </div>
    </div>

    <div class="form-group center">
      <button type="submit" class="btn btn-default submit">Sign In</button>
    </div>
  </form>
  <div class="center">
    <p>Either email or password was incorrect.</p>
    <p>New user? Please <a href="signUp.jsp">sign up</a>.</p>
  </div>
</div>
</body>
</html>