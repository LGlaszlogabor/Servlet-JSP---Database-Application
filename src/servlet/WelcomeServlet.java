package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.DatabaseService;

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User u = (User)request.getSession().getAttribute("user");
        request.setAttribute("first_name", u.getFirstName());
		request.setAttribute("last_name", u.getLastName());
		request.setAttribute("user_name", u.getUserName());
		request.setAttribute("email", u.getEmail());
		request.setAttribute("adress", u.getAdress());
        if (request.getParameter("modify") != null) {
        	request.setAttribute("opt", "modify");
           	request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
        }else if(request.getParameter("cancel") != null){
        	request.setAttribute("opt", "cancel");
           	request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
        }else if(request.getParameter("save") != null){
        	 String name = request.getParameter("name");
    	     String pass1 = request.getParameter("password1");
    	     String pass2 = request.getParameter("password2");
    	     String first_name = request.getParameter("first_name");
    	     String last_name = request.getParameter("last_name");
    	     String adress = request.getParameter("adress");
    	     String email = request.getParameter("email");
    	     int status = 0;
    	     User userr = (User) request.getSession().getAttribute("user");
    	     String message = "";
    	     String content = "";
    	     if(name.isEmpty() || pass1.isEmpty()|| pass2.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || adress.isEmpty() || email.isEmpty()){
    	    	 status = -1;
    	    	 message = "Please fill all the fields";
    	     }
    	     else if(pass1.equals(pass2)){
    			 List<User> userList = DatabaseService.getInstance().getUsersByFilter(name);
    			 for(User us:userList){
    			 	if(us.getUserName().equals(name)){
    					status = 1;
    					message = "An user with that username exists.";
    					break;
    				}
    			 }
    			 if(status == 0){
    			 	message = "Successful update.";
    			 	User user = new User(userr.getId(),first_name,last_name,name,email,pass1,adress,"0");
    			 	DatabaseService.getInstance().updateUser(user);	
    			 	request.getSession().removeAttribute("user");
    				request.getSession().setAttribute("user", user);
    				   request.setAttribute("first_name", user.getFirstName());
    					request.setAttribute("last_name", user.getLastName());
    					request.setAttribute("user_name", user.getUserName());
    					request.setAttribute("email", user.getEmail());
    					request.setAttribute("adress", user.getAdress());
    			 }	
    	     }
    		 else{
    			message = "Passwords must match.";
    		 }
    	     if (status != 0){
    	    	 content += "<form action='Welcome' method='post'>"+
         				"<input type='submit' name='modify' value='Modify Data' />"+
         				"</form>"+
         				"<h3>"+message+"</h3>";
    	     }
    	     else {
    	    	 content += "<form action='Welcome' method='post'>"+
         				 	"<input type='submit' name='modify' value='Modify Data' />"+
         				 	"</form>";
    	     }
    	     request.setAttribute("content", content);
    	     request.setAttribute("opt", "save");
    	     
            request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
        }else if(request.getParameter("logout") != null){
        	request.getSession().removeAttribute("user");
        	request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
       
    	
     } 
	
}