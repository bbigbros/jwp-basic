<%--
  Created by IntelliJ IDEA.
  User: stripes
  Date: 2017. 2. 14.
  Time: PM 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file ="../partial/header.jsp" %>
</head>
<body>
<%@ include file = "../partial/navigation.jsp" %>
<div class="container" id="main">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading"><h4>Profiles</h4></div>
            <div class="panel-body">
                <div class="well well-sm">
                    <div class="media">
                        <a class="thumbnail pull-left" href="#">
                            <img class="media-object" src="../images/80-text.png">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">${user.name}</h4>
                            <p>
                                <a href="#" class="btn btn-xs btn-default"><span class="glyphicon glyphicon-envelope"></span>&nbsp;${user.email}</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file = "../partial/footer.jsp" %>
</body>
</html>