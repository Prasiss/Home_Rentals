package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.HomeRentals.dao.HomeRentalsDAO;
import com.HomeRentals.utils.ValidationUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");

        if (!ValidationUtil.isValidString(fullName) ||
                !ValidationUtil.isValidString(username) ||
                !ValidationUtil.isValidString(email) ||
                !ValidationUtil.isValidString(number) ||
                !ValidationUtil.isValidString(password)) {
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }

        try {
            HomeRentalsDAO dao = new HomeRentalsDAO();
            boolean success = dao.insertUser(fullName, username, email, number, password);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                request.setAttribute("error", "Registration failed. Try again.");
                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Server error occurred");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        }
    }
}