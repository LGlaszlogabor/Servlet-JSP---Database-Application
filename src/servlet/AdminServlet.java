package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.DatabaseService;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean isLogin = true;
        if (request.getParameter("delete") != null) {
        	DatabaseService.getInstance().deleteUser(new User(Long.parseLong(request.getParameter("toDelete")),"","","","","","",""));
        }
        else if (request.getParameter("logout") != null) {
        	
         	request.getRequestDispatcher("/Login.jsp").forward(request, response);
         	isLogin = false;
        }
        if(isLogin){
            request.getRequestDispatcher("/Admin.jsp").forward(request, response);
        }
    }  
}