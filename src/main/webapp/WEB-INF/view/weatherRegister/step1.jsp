<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="vipMember.register.title"/></title>
</head>
<body>
<h2><spring:message code="vipMember.info"/></h2>
<form:form action="step2" modelAttribute="city">
    <h3><spring:message code="locationCode"/></h3>
    <p>
        <label><spring:message code="bigCity"/>:<br>
            <form:input path="bigCity"/>
            <form:errors path="bigCity"/>
        </label>
    </p>
    <p>
        <label><spring:message code="middleCity"/>:<br>
            <form:input path="middleCity"/>
        </label>
    </p>
    <p>
        <label><spring:message code="smallCity"/>:<br>
            <form:input path="smallCity"/>
        </label>
    </p>
    <input type="submit" value="<spring:message code="register.btn"/>">
    <form:errors/>
</form:form>
</body>