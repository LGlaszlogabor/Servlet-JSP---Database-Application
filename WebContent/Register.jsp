<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="internationalization.text" />
<!DOCTYPE html>
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<%
    session.removeAttribute("user");
%>
<body>
		<form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="hu" ${language == 'hu' ? 'selected' : ''}>Hungarian</option>
                <option value="ro" ${language == 'ro' ? 'selected' : ''}>Romanian</option>
            </select>
        </form>
	 <form method="post" action="Register">
        User name:<input type="text" name="name" /><br/>
        Password:<input type="password" name="password1" /><br/>
        Password again:<input type="password" name="password2" /><br/>
        First Name:<input type="text" name="first_name" /><br/>
        Last Name:<input type="text" name="last_name" /><br/>
        Email:<input type="text" name="email" /><br/>
        Adress:<input type="text" name="adress" /><br/>
        <input type="submit" value="register" />
     </form>	
	  <h1>${message}</h1>
      <a href="Login.jsp" class="button">Go to login.</a>
</body>
</html>