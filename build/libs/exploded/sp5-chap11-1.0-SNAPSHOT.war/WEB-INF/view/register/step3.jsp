<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="member.register.title"/></title>
</head>
<body>
<p>
    <spring:message code="register.done"/>
</p>
<p>
    <a href="<c:url value='/myToday'/>">[<spring:message code="go.main.btn"/>]</a>
</p>
</body>
</html>