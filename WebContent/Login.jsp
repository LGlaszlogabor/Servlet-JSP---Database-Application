<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="internationalization.text" />
<!DOCTYPE html>
<html lang="${language}">
<head>
	<title>Login</title>
</head>
<body>
<%
	session.removeAttribute("user");
%>
		<form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}><label for="en"><fmt:message key="lang.en" /></label></option>
                <option value="hu" ${language == 'hu' ? 'selected' : ''}><label for="hu"><fmt:message key="lang.hu" /></label></option>
                <option value="ro" ${language == 'ro' ? 'selected' : ''}><label for="ro"><fmt:message key="lang.ro" /></label></option>
            </select>
        </form>
	 <form method="post" action="Login">
        <label for="username"><fmt:message key="login.label.username" />:</label>
        <input type="text" name="name" id="username" /><br/>
        <label for="username"><fmt:message key="login.label.password" />:</label>
        <input type="password" name="password" /><br/>
        <fmt:message key="login.button.submit" var="buttonValue" />
        <input type="submit" name="submit" value="${buttonValue}">
     </form>
     <h1>${message}</h1>
     <a href="Register.jsp" > <fmt:message key="login.label.register" /></a>
</body>
</html>