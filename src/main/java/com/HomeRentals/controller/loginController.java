package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.HomeRentals.model.UserModel;
import com.HomeRentals.service.LoginService;
import com.HomeRentals.utils.SessionUtil;
import com.HomeRentals.utils.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/client/login.jsp").forward(request, response);
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String username = request.getParameter("username");
			String password =request.getParameter("password");
			
			
			if (!ValidationUtil.isValidString(username) || !ValidationUtil.isValidString(password)) {
		        request.setAttribute("error", "Username and Password are required");
		        request.getRequestDispatcher("/WEB-INF/pages/client/login.jsp").forward(request, response);
		        return;
		    }
			LoginService login =new LoginService();
			UserModel user = login.authenticate(username, password);
			if (user != null) {
				SessionUtil.setAttribute(request, "user", user, 3600);
			    SessionUtil.setAttribute(request, "role", user.getRole(), 3600);

			    response.sendRedirect(request.getContextPath() + "/userdashboard");
	       
	        } else {
	            request.setAttribute("error", "Invalid username or password");
	            request.getRequestDispatcher("/WEB-INF/pages/client/login.jsp")
	                    .forward(request, response);
	        }
		}catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error: " + e.getMessage());
		}
	}

}