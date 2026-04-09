package com.HomeRentals.controller;

import com.HomeRentals.model.User;
import com.HomeRentals.service.DashboardService;
import com.HomeRentals.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin/dashboard", "/admin"})
public class AdminDashboardController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if (!SessionUtil.isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        User user = SessionUtil.getLoggedInUser(request);
        if (!"ADMIN".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }
        
        DashboardService service = new DashboardService();
        Map<String, Object> adminData = service.getAdminDashboardData();
        
        request.setAttribute("stats", adminData.get("stats"));
        request.setAttribute("revenueData", adminData.get("revenueData"));
        request.setAttribute("recentBookings", adminData.get("recentBookings"));
        request.setAttribute("pendingProperties", adminData.get("pendingProperties"));
        request.setAttribute("dealers", adminData.get("dealers"));
        request.setAttribute("recentUsers", adminData.get("recentUsers"));
        request.setAttribute("auditLogs", adminData.get("auditLogs"));
        request.setAttribute("flaggedContent", adminData.get("flaggedContent"));
        request.setAttribute("activePage", "dashboard");
        
        request.getRequestDispatcher("/pages/admin/dashboard.jsp").forward(request, response);
    }
}