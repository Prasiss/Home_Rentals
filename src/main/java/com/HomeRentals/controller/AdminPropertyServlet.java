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
import com.HomeRentals.model.PropertyModel;

@WebServlet("/admin/properties")
public class AdminPropertyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HomeRentalsDAO dao;

    public void init() throws ServletException { dao = new HomeRentalsDAO(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            String statusParam = request.getParameter("status");
            String filter = (statusParam != null && !statusParam.isEmpty()) ? statusParam.toUpperCase() : "PENDING";

            List<PropertyModel> propertyList;
            if ("PENDING".equals(filter)) {
                propertyList = dao.getPendingPropertiesAsModel();
            } else {
                propertyList = dao.getPropertiesByStatus(filter);
            }

            request.setAttribute("propertyList",  propertyList);
            request.setAttribute("currentFilter", filter);
            request.setAttribute("activePage",    "properties");
            request.setAttribute("pageTitle",     "Property Approvals");

            request.getRequestDispatcher("/WEB-INF/pages/admin/properties.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admindashboard");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        try {
            int propertyId = Integer.parseInt(request.getParameter("propertyId"));

            // DAO signature: updatePropertyStatus(int propertyId, String status)
            // No adminId parameter exists in the DAO.
            if ("approveProperty".equals(action)) {
                dao.updatePropertyStatus(propertyId, "APPROVED");
            } else if ("rejectProperty".equals(action)) {
                dao.updatePropertyStatus(propertyId, "REJECTED");
            }

            response.sendRedirect(request.getContextPath() + "/admin/properties");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/properties");
        }
    }
}
