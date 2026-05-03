package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.HomeRentals.dao.HomeRentalsDAO;
import com.HomeRentals.model.User;

@WebServlet("/admin/users")
public class AdminUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HomeRentalsDAO dao;
    public void init() throws ServletException { dao = new HomeRentalsDAO(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            List<User> userList = dao.getAllUsers();
            request.setAttribute("userList", userList);
            request.setAttribute("activePage", "users");
            request.setAttribute("pageTitle", "Manage Users");
            request.getRequestDispatcher("/WEB-INF/views/admin/users.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("AdminUserServlet.doGet failed: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            if ("approveUser".equals(action)) dao.approveUser(userId);
            else if ("deleteUser".equals(action)) dao.softDeleteUser(userId);
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }
    }
}
