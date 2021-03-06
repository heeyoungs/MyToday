<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="member.register.title"/></title>
</head>
<body>
<h2><spring:message code="term"/></h2>
<p><spring:message code="term.info"/></p>
<form action="step2" method="post">
    <label>
        <input type="checkbox" name="agree" value="true">
        <spring:message code="term.agree"/>
    </label>
    <input type="submit" value="<spring:message code="next.btn"/>">
</form>
</body>
</html>