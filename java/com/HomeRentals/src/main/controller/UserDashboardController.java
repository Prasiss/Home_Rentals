package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.HomeRental.dao.HomeRentalDAO;
import com.HomeRental.model.UserModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/userdashboard" })
public class UserDashboardController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private HomeRentalDAO dao;

    public void init() throws ServletException {
        dao = new HomeRentalDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            int userId = (int) session.getAttribute("userId");
            UserModel user = dao.getUserById(userId);

            int totalBookings  = dao.getUserTotalBookings(userId);
            int activeStays    = dao.getUserActiveStays(userId);
            int completedStays = dao.getUserCompletedStays(userId);
            int wishlistCount = dao.getWishlistByUser(userId).size();
            

            request.setAttribute("user",           user);
            request.setAttribute("totalBookings",  totalBookings);
            request.setAttribute("activeStays",    activeStays);
            request.setAttribute("completedStays", completedStays);
            request.setAttribute("wishlistCount",  wishlistCount);

            request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/dashboard.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}