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

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("login".equals(action)) {
            handleLogin(req, resp);
        } else if ("logout".equals(action)) {
            handleLogout(req, resp);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            HomeRentalsDAO dao = new HomeRentalsDAO();
            User user = dao.getUserByEmail(email);

            if (user != null && password.equals(user.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("user_id", user.getUserId());
                session.setAttribute("user_name", user.getFullName());
                session.setAttribute("user_email", user.getEmail());
                session.setAttribute("role_name", user.getRoleName());

                if ("ADMIN".equalsIgnoreCase(user.getRoleName())) {
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/dashboard");
                }
            } else {
                req.setAttribute("errorMsg", "Invalid email or password.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMsg", "Server error. Please try again.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    private void handleLogout(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}