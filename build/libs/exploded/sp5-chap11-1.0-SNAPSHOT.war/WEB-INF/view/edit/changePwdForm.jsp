<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="change.info.title"/></title>
</head>
<body>
<form:form modelAttribute="change">
    <p>
        <label><spring:message code="currentPassword"/>:
            <form:password path="currentPassword"/>
            <form:errors path="currentPassword"/>
        </label>
    </p>
    <p>
        <label><spring:message code="newPassword"/>:
            <form:password path="newPassword"/>
            <form:errors path="newPassword"/>
        </label>
    </p>
    <input type="submit" value="<spring:message code="change.btn"/>">
</form:form>
</body>
</html>