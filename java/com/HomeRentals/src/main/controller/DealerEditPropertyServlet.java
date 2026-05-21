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

@WebServlet(asyncSupported = true, urlPatterns = { "/dealer/edit-property" })
public class DealerEditPropertyServlet extends HttpServlet {

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
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/dealer/properties");
            return;
        }

        try {
            int propertyId = Integer.parseInt(idParam);
            Map<String, Object> property = dao.getDealerPropertyById(propertyId, dealerId);

            if (property == null) {
                response.sendRedirect(request.getContextPath()
                    + "/dealer/properties?error=Property+not+found");
                return;
            }

            List<Map<String, Object>> categories = new ArrayList<>();
            try { categories = dao.getAllCategories(); } catch (Exception ex) { ex.printStackTrace(); }

            request.setAttribute("property",   property);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/WEB-INF/pages/dealer/edit-property.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()
                + "/dealer/properties?error=Could+not+load+property");
        }
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

        try {
            int    propertyId    = Integer.parseInt(request.getParameter("propertyId"));
            String title         = request.getParameter("title");
            String location      = request.getParameter("location");
            String description   = request.getParameter("description");
            double pricePerMonth = Double.parseDouble(request.getParameter("pricePerMonth"));
            int    categoryId    = Integer.parseInt(request.getParameter("categoryId"));
            String availableFrom = request.getParameter("availableFrom");

            dao.updateDealerProperty(propertyId, dealerId, title, location, description,
                                     pricePerMonth, categoryId, availableFrom);

            response.sendRedirect(request.getContextPath()
                + "/dealer/properties?success=Property+updated+and+sent+for+approval");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()
                + "/dealer/properties?error=Failed+to+update+property");
        }
    }
}