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

@WebServlet("/admin/properties")
public class AdminPropertyServlet extends HttpServlet {
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
        String filterStatus = request.getParameter("status");
        try {
            List<Map<String, Object>> propertyList;
            if (filterStatus != null && !filterStatus.isEmpty()) {
                propertyList = dao.getPropertiesByStatus(filterStatus);
            } else {
                propertyList = dao.getPendingProperties();
                filterStatus = "PENDING";
            }
            request.setAttribute("propertyList", propertyList);
            request.setAttribute("currentFilter", filterStatus);
            request.setAttribute("activePage", "properties");
            request.setAttribute("pageTitle", "Property Approvals");
            request.getRequestDispatcher("/WEB-INF/views/admin/properties.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");
        try {
            int propertyId = Integer.parseInt(request.getParameter("propertyId"));
            HomeRentalsDAO dao = new HomeRentalsDAO();
            User admin = dao.getUserById((int) session.getAttribute("user_id"));
            if ("approveProperty".equals(action)) dao.updatePropertyStatus(propertyId, "APPROVED", admin.getUserId());
            else if ("rejectProperty".equals(action)) dao.updatePropertyStatus(propertyId, "REJECTED", admin.getUserId());
            response.sendRedirect(request.getContextPath() + "/admin/properties");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/properties");
        }
    }
}