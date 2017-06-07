<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="tag" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 07.05.2017
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/magnific-popup.css" />" rel="stylesheet">

    <script src="<c:url value="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js" />"></script>
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <script src="<c:url value="https://oss.maxcdn.com/respond/1.4.2/respond.min.js" />"></script>
    <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" />"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><spring:message code="brand"/></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <ul class="nav navbar-nav navbar-left">
                        <li><a href="" class="appnav">Applications</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-left">
                        <li><a href="" class="ordnav">Orders</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-left">
                        <li><a href="" class="blnav">Black List</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="" class="logout">Log out</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav navbar-nav navbar-right">
                        <li><tag:locale param="en"/></li>
                        <li><tag:locale param="ru"/></li>
                        <li><a href="#loginform" class="loginpopup"><spring:message code="login"/></a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container bs-cont">
    <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-1">

        </div>
        <div class="col-md-1">

        </div>
        <div class="col-md-6">
            <table class="table table-striped ">
                <thead>
                <tr class="table-hover">
                    <td></td>
                    <td><spring:message code="make"/></td>
                    <td><spring:message code="price"/></td>
                    <td><spring:message code="submit"/></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${carList}" var="car">
                    <tr>
                        <td><img src="${car.imagePath}"></td>
                        <td style="vertical-align: middle">${car.make}</td>
                        <td style="vertical-align: middle">${car.price}</td>
                        <td style="vertical-align: middle">
                            <a href="#" class="btn btn-primary" data-id="${car.id}" name="formref"><spring:message
                                    code="submit"/></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-1">

        </div>
    </div>
</div>

<div class="hidden">
    <form:form action="" id="forma" commandName="App">
        <div class="form-group">
            <label for="exampleInputEmail1"><spring:message code="firstname"/></label>
            <form:input type="text" class="form-control" id="exampleInputEmail1" placeholder="First Name"
                        path="firstname"/>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1"><spring:message code="lastname"/></label>
            <form:input type="text" class="form-control" id="exampleInputPassword1" placeholder="Last Name"
                        path="lastname"/>
        </div>
        <div class="form-group">
            <label for="exampleInputEmail11"><spring:message code="patronymic"/></label>
            <form:input type="text" class="form-control" id="exampleInputEmail11" placeholder="Patronymic"
                        path="patronymic"/>
        </div>
        <div class="form-group">
            <label for="exampleInputEmail111"><spring:message code="passnum"/></label>
            <form:input type="text" class="form-control" id="exampleInputEmail111" placeholder="Passnum"
                        path="passnum"/>
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1111"><spring:message code="email"/></label>
            <form:input type="email" class="form-control" id="exampleInputEmail1111" placeholder="email" path="email"/>
        </div>
        <div class="form-group">
            <label for="exampleInputEmail11111"><spring:message code="term"/></label>
            <form:input type="text" class="form-control" id="exampleInputEmail11111" placeholder="term" path="term"/>
        </div>
        <input type="submit" id="senders" class="btn-primary" value=<spring:message code="submit"/>>
    </form:form>

    <form action="" id="updateordform">
        <div class="form-group">
            <label for="repcost">repaircost</label>
            <input type="text" class="form-control" id="repcost" placeholder="repaircost" name="repcost">
        </div>

        <input type="submit" id="sendupdate" class="btn-primary" value="Submit">
    </form>
    <form action="" id="loginform" method="POST">
        <div class="form-group">
            <label for="login">Login</label>

            <input type="text" class="form-control" id="login" placeholder="login" name="login">

        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="text" class="form-control" id="password" placeholder="password" name="password">
        </div>
        <input type="submit" id="send" class="btn-primary" value="Submit">
    </form>
    <form action="" id="addblacklist">
        <div class="form-group">
            <label for="addbl">repaircost</label>
            <input type="text" class="form-control" id="addbl" placeholder="passnum" name="passnum">
        </div>

        <input type="submit" id="sendaddbl" class="btn-primary" value="Submit">
    </form>
    <form method="post" action="/logout" class="logout1"/>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.magnific-popup.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.bootstrap-growl.min.js" />"></script>
</body>
</html>
