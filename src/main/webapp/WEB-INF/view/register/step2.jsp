<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="member.register.title"/></title>
</head>
<body>
<h2><spring:message code="member.info"/></h2>
<form:form action="step3" modelAttribute="member">
    <p>
        <label><spring:message code="id"/>:<br>
            <form:input path="id"/>
            <form:errors path="id"/>
        </label>
    </p>
    <p>
        <label><spring:message code="password"/>:<br>
            <form:password path="password"/>
            <form:errors path="password"/>
        </label>
    </p>
    <p>
        <label><spring:message code="name"/>:<br>
            <form:input path="name"/>
            <form:errors path="name"/>
        </label>
    </p>
    <p>
        <label><spring:message code="email"/>:<br>
            <form:input path="email"/>
            <form:errors path="email"/>
        </label>
    </p>
    <input type="submit" value="<spring:message code="register.btn"/>">
</form:form>
</body>