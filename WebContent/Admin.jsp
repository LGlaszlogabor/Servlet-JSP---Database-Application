<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="model.User" %>
    <%@ page import="service.DatabaseService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body>
	<h1>Welcome Admin.</h1>
	<form method = 'post' action='Admin'>
		 <table border ='1' ><tr>
    			    <th>ID</th>
    			    <th>First Name</th>
    			    <th>Last Name</th>
    			    <th>User Name</th>
    			    <th>Email</th>
    			    <th>Password</th>
    			    </tr>
           <% 
           String allUsers = "";
           List<User> userList = DatabaseService.getInstance().getAllUsers();
            		for(User u: userList){
            			if(u.getLevel().equals("0")){
            			allUsers += "<tr>"+
            					"<td>"+u.getId()+"</td>"+
            					"<td>"+u.getFirstName()+"</td>"+
            					"<td>"+u.getLastName()+"</td>"+
            					"<td>"+u.getUserName()+"</td>"+
            					"<td>"+u.getEmail()+"</td>"+
            					"<td>"+u.getPassword()+"</td>"+
            					"<td><input type='radio' name='toDelete' value="+u.getId()+"></td>"+
            					"</tr>";
            			}
            		}
            	out.print(allUsers);
          %>
          </table>
		<input type='submit' name = 'delete' value = 'Delete'>
		<input type='submit' name = 'logout' value = 'Logout'>
	</form>
</body>
</html>