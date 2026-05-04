package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.HomeRentals.dao.HomeRentalsDAO;
import com.HomeRentals.model.User;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            int userId = (int) session.getAttribute("user_id");
            HomeRentalsDAO dao = new HomeRentalsDAO();
            User user = dao.getUserById(userId);
            int totalBookings = dao.getUserTotalBookings(userId);
            int activeStays = dao.getUserActiveStays(userId);
            int completedStays = dao.getUserCompletedStays(userId);

            request.setAttribute("user", user);
            request.setAttribute("totalBookings", totalBookings);
            request.setAttribute("activeStays", activeStays);
            request.setAttribute("completedStays", completedStays);

            request.getRequestDispatcher("/pages/user/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/dashboard");
        }
    }
}