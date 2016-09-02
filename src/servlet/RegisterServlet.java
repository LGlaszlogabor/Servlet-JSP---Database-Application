package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.DatabaseService;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
        String name = request.getParameter("name");
        String pass1 = request.getParameter("password1");
        String pass2 = request.getParameter("password2");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String adress = request.getParameter("adress");
        String email = request.getParameter("email");
        int status = 0;
        String message = "";
        if(name.isEmpty() || pass1.isEmpty()|| pass2.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || adress.isEmpty() || email.isEmpty()){
        	message = "Please fill all the fields";
        }
        else if(pass1.equals(pass2)){
			List<User> userList = DatabaseService.getInstance().getUsersByFilter(name);
			for(User u:userList){
				if(u.getUserName().equals(name)){
					status = 1;
					message = "An user with that username exists.";
				}
			}
			if(status == 0){
				message = "Successful registration";
				DatabaseService.getInstance().insertUser(new User(first_name,last_name,name,email,pass1,adress,"0"));
			}	
		}
	    else{
			message = "Passwords must match.";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/Register.jsp").forward(request, response);
    }
}