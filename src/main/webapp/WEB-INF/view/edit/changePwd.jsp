<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="change.info.title"/></title>
</head>
<body>
<p>
    <spring:message code="change.info.done"/>
</p>
<p>
    <a href="<c:url value='/myToday'/>">[<spring:message code="go.main.btn"/>]</a>
</p>
</body>
</html>