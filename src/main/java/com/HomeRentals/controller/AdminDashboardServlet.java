package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.HomeRentals.dao.HomeRentalsDAO;
import com.HomeRentals.model.User;

@WebServlet({"/admin", "/admin/dashboard"})
public class AdminDashboardServlet extends HttpServlet {
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
        String role = (String) session.getAttribute("role_name");
        if (!"ADMIN".equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        try {
            Map<String, Object> stats = dao.getDashboardStats();
            List<User> recentUsers = dao.getRecentUsers(5);
            List<Map<String, Object>> pendingProperties = dao.getPendingProperties();
            request.setAttribute("stats", stats);
            request.setAttribute("recentUsers", recentUsers);
            request.setAttribute("pendingProperties", pendingProperties);
            request.setAttribute("activePage", "dashboard");
            request.setAttribute("pageTitle", "Dashboard");
            request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }
}