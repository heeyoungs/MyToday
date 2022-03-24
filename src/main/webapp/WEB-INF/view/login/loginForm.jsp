<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="login.title"/></title>
</head>
<body>
<form:form modelAttribute="login">
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
        <label><spring:message code="rememberId"/>:
            <form:checkbox path="rememberId"/>
        </label>
    </p>
    <input type="submit" value="<spring:message code="login.btn"/>"/>
    <form:errors/>
</form:form>
<p>
    <a href="<c:url value='/register/step1'/>">
        [<spring:message code="register"/>]
    </a>
</p>
</form>
</body>
</html>
