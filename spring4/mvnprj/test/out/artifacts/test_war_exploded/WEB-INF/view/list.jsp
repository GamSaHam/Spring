<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hgs
  Date: 2019-05-02
  Time: 오후 6:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <ul>
    <c:forEach var="user" items="${users}">
        <li>${user.id}</li>


    </c:forEach>
    </ul>

</body>
</html>
