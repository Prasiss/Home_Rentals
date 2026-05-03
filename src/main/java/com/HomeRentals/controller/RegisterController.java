package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.HomeRentals.service.*;

import com.HomeRentals.utils.*;
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/client/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fullName = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        String confirmPassword =request.getParameter("confirmpassword");
        RegisterService registerService = new RegisterService();
        
        if (!ValidationUtil.isValidString(fullName) ||
                !ValidationUtil.isValidString(username) ||
                !ValidationUtil.isValidString(email) ||
                !ValidationUtil.isValidString(number) ||
                !ValidationUtil.isValidString(password)) {

                request.setAttribute("error", "All fields are required");
                request.getRequestDispatcher("/pages/client/register.jsp").forward(request, response);
                return;
        }
        if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match");
                request.getRequestDispatcher("/pages/client/register.jsp").forward(request, response);
                return;
        }
        
        // Call service to add student
        try {
            boolean success = registerService.addUser(fullName, username, email, number, password);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                request.setAttribute("error", "Registration failed. Try again.");
                request.getRequestDispatcher("/pages/client/register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Server error occurred");
            request.getRequestDispatcher("/pages/client/register.jsp").forward(request, response);
        }
	}
}