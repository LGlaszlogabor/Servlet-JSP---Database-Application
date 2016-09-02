<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="model.User" %>
    <%@ page import="service.DatabaseService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<h1>Welcome ${first_name} ${last_name}</h1>
	Your data:<br>
	<h5>First name: ${first_name}</h5>
	<h5>Last name: ${last_name}</h5>
	<h5>User name: ${user_name}</h5>
	<h5>Email: ${email}</h5>
	<h5>Adress: ${adress}</h5>
	<%	
	String content = "";
    boolean isLogin = true;
    User u = (User)request.getSession().getAttribute("user");
    if (request.getAttribute("opt").equals("modify")) {
        content += "<form action='Welcome' method='post'>"+
        			"User name:<input type='text' name='name' value='"+u.getUserName()+"' /><br/>"+
        	        "Password:<input type='password' name='password1' /><br/>"+
        	        "Password again:<input type='password' name='password2' /><br/>"+
        	        "First Name:<input type='text' name='first_name' value='"+u.getFirstName()+"' /><br/>"+
        	        "Last Name:<input type='text' name='last_name' value='"+u.getLastName()+"' /><br/>"+
        	        "Email:<input type='text' name='email' value='"+u.getEmail()+"' /><br/>"+
        	        "Adress:<input type='text' name='adress' value='"+u.getAdress()+"' /><br/>"+
        	        "<input type='submit' name='save' value='Save' />"+
        	        "<input type='submit' name='cancel' value='Cancel' />"+
        	        "</form>";
    }else if(request.getAttribute("opt").equals("cancel")){
    	content += "<form action='Welcome' method='post'>"+
    				"<input type='submit' name='modify' value='Modify Data' />"+
    				"</form>";
    }else if(request.getAttribute("opt").equals("save")){
    	%>
    	${content}
    <%
    }
	out.print(content);
	%>
	<form action='Welcome' method='post'>
    	<input type='submit' name='logout' value='Logout' />
    </form>
</body>
</html>