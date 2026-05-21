package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.HomeRental.dao.HomeRentalDAO;

@WebServlet(asyncSupported = true, urlPatterns = { "/booking", "/bookings" })
public class BookingController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private HomeRentalDAO dao = new HomeRentalDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            int userId = (int) session.getAttribute("userId");
            String filter = request.getParameter("status");

            java.util.List<java.util.Map<String, Object>> bookings;
            if (filter != null && !filter.isEmpty() && !filter.equalsIgnoreCase("all")) {
                bookings = dao.getBookingsByUserAndStatus(userId, filter);
            } else {
                bookings = dao.getBookingsByUser(userId);
            }

            request.setAttribute("bookings", bookings);
            request.setAttribute("activeFilter", filter != null ? filter.toUpperCase() : "ALL");

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/bookings.jsp")
               .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}