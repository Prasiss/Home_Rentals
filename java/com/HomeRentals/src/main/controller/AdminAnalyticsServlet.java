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

@WebServlet(asyncSupported = true, urlPatterns = { "/admin/analytics" })
public class AdminAnalyticsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminAnalyticsServlet() { super(); }

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
            // ── Summary KPIs ───────────────────────────────────────────────
            long   totalRevenue       = dao.getTotalRevenue();
            int    totalUsers         = dao.getTotalUsersCount();
            int    totalDealers       = dao.getActiveDealersCount();
            int    activeProperties   = dao.getActivePropertiesCount();
            int    totalBookings      = dao.getTotalBookingsCount();
            int    completedBookings  = dao.getCompletedBookingsCount();
            long   avgRevenue         = completedBookings > 0 ? totalRevenue / completedBookings : 0;

           
            List<String[]> monthlyRevenue    = dao.getMonthlyRevenue();
            List<String[]> monthlyUsers      = dao.getMonthlyUserRegistrations();
            List<String[]> monthlyProperties = dao.getMonthlyPropertyRegistrations();
            List<String[]> monthlyBookings   = dao.getMonthlyBookingCount();


            List<String[]> bookingStatus = dao.getBookingStatusBreakdown();         
            List<String[]> propertyStatus = dao.getPropertyStatusBreakdown();   
            List<String[]> topLocations = dao.getTopLocations();
            int totalRegularUsers = dao.getTotalRegularUsersCount();

            request.setAttribute("totalRevenue",      totalRevenue);
            request.setAttribute("totalUsers",        totalUsers);
            request.setAttribute("totalDealers",      totalDealers);
            request.setAttribute("activeProperties",  activeProperties);
            request.setAttribute("totalBookings",     totalBookings);
            request.setAttribute("completedBookings", completedBookings);
            request.setAttribute("avgRevenue",        avgRevenue);

            request.setAttribute("monthlyRevenue",    monthlyRevenue);
            request.setAttribute("monthlyUsers",      monthlyUsers);
            request.setAttribute("monthlyProperties", monthlyProperties);
            request.setAttribute("monthlyBookings",   monthlyBookings);

            request.setAttribute("bookingStatus",     bookingStatus);
            request.setAttribute("propertyStatus",    propertyStatus);
            request.setAttribute("topLocations",      topLocations);
            request.setAttribute("totalRegularUsers", totalRegularUsers);

            request.setAttribute("activePage", "analytics");
            request.setAttribute("pageTitle",  "Analytics");

            request.getRequestDispatcher("/WEB-INF/pages/admin/analytics.jsp")
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
