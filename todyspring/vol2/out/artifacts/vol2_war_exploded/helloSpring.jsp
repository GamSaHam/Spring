<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="springbook.learningTest.spring.mvc.controller.HelloSpring" %><%--
  Created by IntelliJ IDEA.
  User: hgs
  Date: 2019-05-01
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

  ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());

  HelloSpring helloSpring = context.getBean(HelloSpring.class);

  System.out.println(helloSpring.sayHello("Root Context"));



%>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
$END$
</body>
</html>
