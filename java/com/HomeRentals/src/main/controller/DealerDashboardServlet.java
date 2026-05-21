package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.HomeRental.dao.HomeRentalDAO;

@WebServlet(asyncSupported = true, urlPatterns = { "/dealer/dashboard", "/dealer" })
public class DealerDashboardServlet extends HttpServlet {

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

        int    dealerId   = (int)    session.getAttribute("userId");
        String dealerName = (String) session.getAttribute("username");

        Map<String, Object>       stats            = new HashMap<>();
        List<Map<String, Object>> recentProperties = new ArrayList<>();
        List<Map<String, Object>> recentBookings   = new ArrayList<>();

        try {
            stats            = dao.getDealerStats(dealerId);
            recentProperties = dao.getRecentDealerProperties(dealerId, 5);
            recentBookings   = dao.getRecentDealerBookings(dealerId, 5);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Could not load dashboard data: " + e.getMessage());
        }

        request.setAttribute("dealerName",       dealerName);
        request.setAttribute("stats",            stats);
        request.setAttribute("recentProperties", recentProperties);
        request.setAttribute("recentBookings",   recentBookings);
        request.getRequestDispatcher("/WEB-INF/pages/dealer/dashboard.jsp").forward(request, response);
    }
}
