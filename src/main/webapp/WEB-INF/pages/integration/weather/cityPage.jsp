<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 05.09.2015
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/jsp.css" var="jspCSS"/>
    <link href="${jspCSS}" rel="stylesheet"/>
    <title>City page</title>
</head>
<body>
<div class="content">
    <c:if test="${empty error}">
        <c:url var="saveUrl" value="/weather"/>
        <h2 class="book">Выберите город</h2>
        <form:form modelAttribute="table" method="POST" acceptCharset="${saveUrl}">
            <form:select path="city" items="${data}" cssClass="bookInput"/>
            <div style="height: 30px"></div>
            <input type="submit" value="Submit" class="book">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form:form>
    </c:if>
    <c:if test="${!empty error}">
        <h2 class="book">Произошла внутреняя ошибка на сервере интеграции</h2>
    </c:if>
    <div style="height: 30px"></div>
    <a class="book" href="/">Назад</a>
</div>
</body>
</html>
