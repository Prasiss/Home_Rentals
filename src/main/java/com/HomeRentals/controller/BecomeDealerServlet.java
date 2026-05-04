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

@WebServlet("/become-dealer")
public class BecomeDealerServlet extends HttpServlet {
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

        try {
            int userId = (int) session.getAttribute("user_id");
            User user = dao.getUserById(userId);
            if (user != null) {
                request.setAttribute("dealerRequestStatus", user.getDealerRequest());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/pages/user/become-dealer.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String companyName    = request.getParameter("companyName");
        String yearsExp       = request.getParameter("yearsExperience");
        String propertiesCount = request.getParameter("propertiesCount");
        String aboutBusiness  = request.getParameter("aboutBusiness");

        if (companyName == null || companyName.trim().isEmpty()
                || aboutBusiness == null || aboutBusiness.trim().isEmpty()) {
            request.setAttribute("errorMsg", "Please fill in all required fields.");
            request.getRequestDispatcher("/pages/user/become-dealer.jsp").forward(request, response);
            return;
        }

        try {
            int userId = (int) session.getAttribute("user_id");

            // Check if request is already pending or user is already a dealer
            User user = dao.getUserById(userId);
            if (user != null && "PENDING".equals(user.getDealerRequest())) {
                request.setAttribute("errorMsg", "You already have a pending dealer request. Please wait for admin review.");
                request.setAttribute("dealerRequestStatus", "PENDING");
                request.getRequestDispatcher("/pages/user/become-dealer.jsp").forward(request, response);
                return;
            }
            if (user != null && user.getRoleId() == 2) {
                request.setAttribute("errorMsg", "You are already a dealer.");
                request.getRequestDispatcher("/pages/user/become-dealer.jsp").forward(request, response);
                return;
            }

            // Save the dealer request to the database
            boolean saved = dao.submitDealerRequest(userId, companyName.trim(),
                    yearsExp, propertiesCount, aboutBusiness.trim());

            if (saved) {
                session.setAttribute("dealerSuccess", "Your dealer request has been submitted and is pending admin review.");
                response.sendRedirect(request.getContextPath() + "/dashboard");
            } else {
                request.setAttribute("errorMsg", "Failed to submit request. Please try again.");
                request.getRequestDispatcher("/pages/user/become-dealer.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "Server error: " + e.getMessage());
            request.getRequestDispatcher("/pages/user/become-dealer.jsp").forward(request, response);
        }
    }
}
