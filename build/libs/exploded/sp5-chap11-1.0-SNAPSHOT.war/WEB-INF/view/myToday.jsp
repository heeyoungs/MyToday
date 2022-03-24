<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    Date nowTime = new Date();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss");
%>
<html>
<head>
    <title><spring:message code="main.title"/></title>
</head>
<body>
<h1>메인 페이지입니다~!</h1>
<p>현재 날짜와 시간은 <%= sf.format(nowTime) %> 입니다.</p>
<c:if test="${empty memberInfo}">
    <p>현재는 날씨 정보를 제공해드리고 있습니다.</p>
    <p>
        <a href="<c:url value="/register/step1"/>">[<spring:message code="register"/> ]</a>
        <a href="<c:url value="/login"/>">[<spring:message code="login"/>]</a>
    </p>
</c:if>

<c:if test="${!empty memberInfo}">
    <p>${memberInfo.name}님 환영합니다~</p>

    <c:if test="${!empty weatherInfo}">
        <h2>날씨 정보</h2>
        <c:out value="강수형태:${weatherInfo.PTY} <- 없음(0),비(1),비/눈(2),눈(3)"/><br>
        <c:out value="습도:${weatherInfo.REH}"/><br>
        <c:out value="1시간 강수량:${weatherInfo.RN1}"/><br>
        <c:out value="기온:${weatherInfo.TH1}"/><br>
        <c:out value="동서바람성분:${weatherInfo.UUU}"/><br>
        <c:out value="남북바람성분:${weatherInfo.VVV}"/><br>
        <c:out value="풍향:${weatherInfo.VEC}"/><br>
        <c:out value="풍속:${weatherInfo.WSD}"/><br>
    </c:if>

    <c:if test="${empty weatherInfo}">
        <a href="<c:url value="/weatherRegister/step1"/>">[<spring:message code="WeatherRegister.btn"/>]</a>
    </c:if>
    <a href="<c:url value="/edit/changePwd"/>">[<spring:message code="changePwd.btn"/>]</a>
    <a href="<c:url value="/logout"/>">[<spring:message code="logout"/>]</a>
</c:if>
</body>
</html>