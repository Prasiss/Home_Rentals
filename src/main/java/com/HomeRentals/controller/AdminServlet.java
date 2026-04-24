package com.homerental.controller;

import com.homerental.model.*;
import com.homerental.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet({"/admin", "/admin/dashboard", "/admin/users", "/admin/dealers", "/admin/properties", "/admin/applications"})
public class AdminServlet extends HttpServlet {
    
    private AdminService adminService;
    
    @Override
    public void init() { adminService = new AdminService(); }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (!"ADMIN".equals(loggedInUser.getRole())) {
            res.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }
        
        session.setAttribute("adminName", loggedInUser.getFullName());
        session.setAttribute("adminRole", "Administrator");
        session.setAttribute("adminInitial", loggedInUser.getFullName().substring(0, 1).toUpperCase());
        session.setAttribute("adminNo", loggedInUser.getUserNo());
        
        String path = req.getServletPath();
        String filter = req.getParameter("status");
        String page;
        
        switch (path) {
            case "/admin/users":
                req.setAttribute("userList", adminService.getAllUsers());
                req.setAttribute("currentFilter", filter != null ? filter : "all");
                page = "/WEB-INF/views/admin/user-list.jsp";
                break;
            case "/admin/dealers":
                req.setAttribute("dealerList", adminService.getAllDealers());
                req.setAttribute("currentFilter", filter != null ? filter : "all");
                page = "/WEB-INF/views/admin/dealer-list.jsp";
                break;
            case "/admin/properties":
                List<Property> props;
                if (filter != null && !filter.isEmpty() && !"pending".equals(filter))
                    props = adminService.getPropertiesByStatus(filter);
                else props = adminService.getPendingProperties();
                req.setAttribute("pendingPropertiesList", props);
                req.setAttribute("currentFilter", filter != null ? filter : "pending");
                page = "/WEB-INF/views/admin/property-approvals.jsp";
                break;
            case "/admin/applications":
                List<DealerApplication> apps;
                if (filter != null && !filter.isEmpty() && !"pending".equals(filter))
                    apps = adminService.getApplicationsByStatus(filter);
                else apps = adminService.getPendingApplications();
                req.setAttribute("pendingApplicationsList", apps);
                req.setAttribute("currentFilter", filter != null ? filter : "pending");
                page = "/WEB-INF/views/admin/applications.jsp";
                break;
            default:
                req.setAttribute("dashboardSummary", adminService.getDashboardSummary());
                req.setAttribute("recentUsersList", adminService.getRecentUsers());
                req.setAttribute("recentDealersList", adminService.getRecentDealers());
                req.setAttribute("pendingPropertiesList", adminService.getPendingProperties());
                page = "/WEB-INF/views/admin/dashboard.jsp";
                break;
        }
        req.getRequestDispatcher(page).forward(req, res);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        String action = req.getParameter("action");
        String servletPath = req.getServletPath();
        String redirect = req.getContextPath() + servletPath;
        
        // User Management Actions
        if ("suspendUser".equals(action)) {
            adminService.suspendUser(Integer.parseInt(req.getParameter("userNo")));
        } else if ("reactivateUser".equals(action)) {
            adminService.reactivateUser(Integer.parseInt(req.getParameter("userNo")));
        } else if ("deleteUser".equals(action)) {
            adminService.deleteUser(Integer.parseInt(req.getParameter("userNo")));
        }
        // Dealer Management Actions
        else if ("suspendDealer".equals(action)) {
            adminService.suspendDealer(Integer.parseInt(req.getParameter("userNo")));
        } else if ("reactivateDealer".equals(action)) {
            adminService.reactivateDealer(Integer.parseInt(req.getParameter("userNo")));
        } else if ("deleteDealer".equals(action)) {
            adminService.deleteDealer(Integer.parseInt(req.getParameter("userNo")));
        }
        // Property Actions
        else if ("approveProperty".equals(action)) {
            adminService.approveProperty(Integer.parseInt(req.getParameter("propertyNo")));
        } else if ("rejectProperty".equals(action)) {
            adminService.rejectProperty(Integer.parseInt(req.getParameter("propertyNo")));
        }
        // Application Actions
        else if ("approveApplication".equals(action)) {
            adminService.approveApplication(Integer.parseInt(req.getParameter("applicationNo")), (Integer) session.getAttribute("adminNo"));
        } else if ("rejectApplication".equals(action)) {
            adminService.rejectApplication(Integer.parseInt(req.getParameter("applicationNo")), (Integer) session.getAttribute("adminNo"));
        }
        
        String filter = req.getParameter("currentFilter");
        if (filter != null && !filter.isEmpty()) redirect += "?status=" + filter;
        res.sendRedirect(redirect);
    }
}