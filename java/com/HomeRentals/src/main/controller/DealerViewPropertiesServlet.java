package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.HomeRental.dao.HomeRentalDAO;

@WebServlet(asyncSupported = true, urlPatterns = { "/dealer/properties" })
public class DealerViewPropertiesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private HomeRentalDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new HomeRentalDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int dealerId = (int) session.getAttribute("userId");
        String filterStatus = request.getParameter("status");
        List<Map<String, Object>> properties = new ArrayList<>();

        try {
            if (filterStatus != null && !filterStatus.isEmpty()) {
                properties = dao.getDealerPropertiesByStatus(dealerId, filterStatus);
            } else {
                properties = dao.getAllDealerProperties(dealerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Could not load properties: " + e.getMessage());
        }

        if (filterStatus == null || filterStatus.isEmpty()) filterStatus = "ALL";
        request.setAttribute("properties",    properties);
        request.setAttribute("currentFilter", filterStatus);
        request.getRequestDispatcher("/WEB-INF/pages/dealer/properties.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int dealerId = (int) session.getAttribute("userId");
        String action = request.getParameter("action");

        if ("deactivateProperty".equals(action)) {
            try {
                int propertyId = Integer.parseInt(request.getParameter("propertyId"));
                boolean success = dao.deactivateDealerProperty(propertyId, dealerId);
                if (success) {
                    response.sendRedirect(request.getContextPath()
                            + "/dealer/properties?deactivated=1");
                } else {
                    response.sendRedirect(request.getContextPath()
                            + "/dealer/properties?error=notfound");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath()
                        + "/dealer/properties?error=failed");
            }
            return;
        }

        response.sendRedirect(request.getContextPath() + "/dealer/properties");
    }
}