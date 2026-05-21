package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.HomeRental.dao.HomeRentalDAO;
import com.HomeRental.model.UserModel;
import com.HomeRental.model.PropertyModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/admindashboard" })
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminController() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        HomeRentalDAO dao = new HomeRentalDAO();
        try {
            long   totalRevenue      = dao.getTotalRevenue();
            int    totalUsers        = dao.getTotalUsersCount();
            int    activeDealers     = dao.getActiveDealersCount();
            int    activeProperties  = dao.getActivePropertiesCount();
            int    pendingUsers      = dao.getPendingUsersCount();
            int    pendingProperties = dao.getPendingPropertiesCount();

            List<UserModel> recentUsers       = dao.getAllUsers();
            List<PropertyModel> pendingPropertyList = dao.getPendingPropertiesAsModel();

            request.setAttribute("totalRevenue",          totalRevenue);
            request.setAttribute("totalUsers",            totalUsers);
            request.setAttribute("activeDealers",         activeDealers);
            request.setAttribute("activeProperties",      activeProperties);
            request.setAttribute("pendingUsers",          pendingUsers);
            request.setAttribute("pendingPropertiesCount",pendingProperties);
            request.setAttribute("recentUsers",           recentUsers);
            request.setAttribute("pendingProperties",     pendingPropertyList);
            request.setAttribute("activePage",            "dashboard");
            request.setAttribute("pageTitle",             "Dashboard");

            request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admindashboard");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}