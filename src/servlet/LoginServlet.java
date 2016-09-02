package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import util.Validator;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Create a session object if it is already not  created.
        HttpSession session = request.getSession(true);
        String name = request.getParameter("name");
        String pass = request.getParameter("password");
        User u;
        if((u = Validator.getUser(name, pass))!= null){
        	session.setAttribute("user", u);
        	if("0".equals(u.getLevel())){
        		request.setAttribute("first_name", u.getFirstName());
        		request.setAttribute("last_name", u.getLastName());
        		request.setAttribute("user_name", u.getUserName());
        		request.setAttribute("email", u.getEmail());
        		request.setAttribute("adress", u.getAdress());
        		request.setAttribute("opt", "cancel");
	           	request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
        	}
        	else{
        		request.getRequestDispatcher("/Admin.jsp").forward(request, response);
        	}
        }
        else{
        	request.setAttribute("message", "Invalid usename or password.");
		    request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
    }  
}