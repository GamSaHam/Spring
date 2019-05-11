<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springFrom" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hgs
  Date: 2019-05-11
  Time: 오후 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Regist Page</title>
</head>
<body>


    <h1>아이디입력</h1>


    <springFrom:form method="POST" action = "/regist.do">

        <table>

        <tr>
            <td><springForm:input path="id"/></td>
            <td><springForm:errors path="id" cssClass="error" /></td>
            <td><input  type="submit" value="생성"/></td>
        </tr>
        </table>
    </springFrom:form>





</body>
</html>
