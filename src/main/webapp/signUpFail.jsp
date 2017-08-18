<%@include file="taglib.jsp"%>
<c:set var="title" value="Sign Up" />
<%@include file="head.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: Holli
  Date: 8/11/2017
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
<html lang="en">

<body>
<div class="container">

  <header class="jumbotron">
    <h1>Mathtastic Games! Sign Up</h1>
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

  <div class="center">
    <p>Failed to create a new account. Already a user? Please <a href="signIn.jsp">sign in</a></p>
  </div>

  <form class="form-horizontal" method="post" action="signUpUser" onsubmit="return validateSignUpForm();" onreset="resetSignUpForm();">

    <div class="form-group">
      <label class="control-label col-xs-4" for="firstName">First Name</label>
      <div class="col-xs-7 col-sm-4">
        <input class="form-control" type="text" name="firstName" id="firstName" />
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-xs-4" for="lastName">Last Name</label>
      <div class="col-xs-7 col-sm-4">
        <input class="form-control" type="text" name="lastName" id="lastName" />
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-xs-4" for="email">Email Address</label>
      <div class="col-xs-7 col-sm-4">
        <input class="form-control" type="text" name="email" id="email" />
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-xs-4" for="password">Password</label>
      <div class="col-xs-7 col-sm-4">
        <input class="form-control" type="password" name="password" id="password" />
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-xs-4" for="confirmPassword">Confirm Password</label>
      <div class="col-xs-7 col-sm-4">
        <input class="form-control" type="password" name="confirmPassword" id="confirmPassword" />
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-xs-4">Date of Birth</label>
      <div class="col-xs-2 col-sm-1">
        <select class="form-control" name="month" id="month">
          <option value="MM">MM</option><option value="01">01</option>
          <option value="02">02</option><option value="03">03</option>
          <option value="04">04</option><option value="05">05</option>
          <option value="06">06</option><option value="07">07</option>
          <option value="8">08</option><option value="09">09</option>
          <option value="10">10</option><option value="11">11</option>
          <option value="12">12</option>
        </select>
      </div>

      <div class="col-xs-2 col-sm-1">
        <select class="form-control" name="day" id="day">
          <option value="DD">DD</option><option value="01">01</option>
          <option value="02">02</option><option value="03">03</option>
          <option value="04">04</option><option value="05">05</option>
          <option value="06">06</option><option value="07">07</option>
          <option value="08">08</option><option value="09">09</option>
          <option value="10">10</option><option value="11">11</option>
          <option value="12">12</option><option value="13">13</option>
          <option value="14">14</option><option value="15">15</option>
          <option value="16">16</option><option value="17">17</option>
          <option value="18">18</option><option value="19">19</option>
          <option value="20">20</option><option value="21">21</option>
          <option value="22">22</option><option value="23">23</option>
          <option value="24">24</option><option value="25">25</option>
          <option value="26">26</option><option value="27">27</option>
          <option value="28">28</option><option value="29">29</option>
          <option value="30">30</option><option value="31">31</option>
        </select>
      </div>

      <div class="col-xs-3 col-sm-2 col-md-2 col-lg-1">
        <input class="form-control" type="text" name="year" id="year" placeholder="YYYY" />
      </div>
    </div>

    <div class="form-group center">
      <button type="submit" class="btn btn-default submit">Sign Up!</button>
      <button type="reset" class="btn btn-default clear">Clear</button>
    </div>

  </form>

  <div class="center">
    <p>Already have an account? Please <a href="signIn.jsp">sign in</a>.</p>
  </div>

</div>
</body>
</html>