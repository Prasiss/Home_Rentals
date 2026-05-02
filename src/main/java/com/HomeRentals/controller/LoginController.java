package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.HomeRentals.dao.HomeRentalsDAO;
import com.HomeRentals.model.User;
import com.HomeRentals.utils.ValidationUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // just show login page - no redirect
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (!ValidationUtil.isValidString(username) || !ValidationUtil.isValidString(password)) {
                request.setAttribute("error", "Username and Password are required");
                request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
                return;
            }

            HomeRentalsDAO dao = new HomeRentalsDAO();
            User user = dao.getUserByUsername(username);

            if (user != null && password.equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user_id", user.getUserId());
                session.setAttribute("user_name", user.getFullName());
                session.setAttribute("user_email", user.getEmail());
                session.setAttribute("role_name", user.getRoleName());

                if ("ADMIN".equalsIgnoreCase(user.getRoleName())) {
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                } else {
                    response.sendRedirect(request.getContextPath() + "/dashboard");
                }
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Server error: " + e.getMessage());
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
    }
}