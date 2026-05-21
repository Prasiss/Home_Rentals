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

@WebServlet(asyncSupported = true, urlPatterns = { "/dealer/add-property" })
public class DealerAddPropertyServlet extends HttpServlet {

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

        List<Map<String, Object>> categories = new ArrayList<>();
        try { categories = dao.getAllCategories(); } catch (Exception e) { e.printStackTrace(); }
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/pages/dealer/add-property.jsp").forward(request, response);
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
            String title         = request.getParameter("title");
            String location      = request.getParameter("location");
            String description   = request.getParameter("description");
            double pricePerMonth = Double.parseDouble(request.getParameter("pricePerMonth"));
            String availableFrom = request.getParameter("availableFrom");
            String catParam      = request.getParameter("categoryId");
            // FIX: was __catParam__ (typo) — now correctly references catParam variable
            int    categoryId    = (catParam != null && !catParam.isEmpty()) ? Integer.parseInt(catParam) : 0;

            dao.insertDealerProperty(dealerId, title, location, description,
                                     pricePerMonth, categoryId, availableFrom);

            response.sendRedirect(request.getContextPath()
                + "/dealer/properties?success=Property+submitted+for+admin+approval");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to add property: " + e.getMessage());
            List<Map<String, Object>> categories = new ArrayList<>();
            try { categories = dao.getAllCategories(); } catch (Exception ex) { ex.printStackTrace(); }
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/WEB-INF/pages/dealer/add-property.jsp").forward(request, response);
        }
    }
}