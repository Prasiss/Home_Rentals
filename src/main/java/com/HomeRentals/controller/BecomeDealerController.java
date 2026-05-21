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

@WebServlet(asyncSupported = true, urlPatterns = { "/dealerapplication" })
public class BecomeDealerController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    HomeRentalDAO dao = new HomeRentalDAO();

    public BecomeDealerController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/become-dealer.jsp")
               .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String companyName = request.getParameter("companyName");
        String yearsExpStr = request.getParameter("yearsExperience");
        String numPropStr  = request.getParameter("propertiesCount");

        // Blank field check
        if (companyName == null || companyName.trim().isEmpty()
                || yearsExpStr == null || yearsExpStr.trim().isEmpty()
                || numPropStr == null || numPropStr.trim().isEmpty()) {
            request.setAttribute("errorMsg", "Please fill in all required fields.");
            request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/become-dealer.jsp")
                   .forward(request, response);
            return;
        }

        // Integer validation for yearsExperience
        int yearsExp;
        try {
            yearsExp = Integer.parseInt(yearsExpStr.trim());
            if (yearsExp < 0 || yearsExp > 60) {
                request.setAttribute("errorMsg", "Years of experience must be a whole number between 0 and 60.");
                request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/become-dealer.jsp")
                       .forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "Years of experience must be a valid whole number (e.g. 3).");
            request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/become-dealer.jsp")
                   .forward(request, response);
            return;
        }

        // Integer validation for numberOfProperties
        int numberOfProperties;
        try {
            numberOfProperties = Integer.parseInt(numPropStr.trim());
            if (numberOfProperties < 0) {
                request.setAttribute("errorMsg", "Number of properties cannot be negative.");
                request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/become-dealer.jsp")
                       .forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMsg", "Number of properties must be a valid whole number (e.g. 5).");
            request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/become-dealer.jsp")
                   .forward(request, response);
            return;
        }

        try {
            int userId = (int) session.getAttribute("userId");
            UserModel user = dao.getUserById(userId);

            // Already a dealer
            if (user != null && "DEALER".equalsIgnoreCase(user.getRole())) {
                request.setAttribute("errorMsg", "You are already a dealer.");
                request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/become-dealer.jsp")
                       .forward(request, response);
                return;
            }

            // Already has a pending request
            if (user != null && !user.isApproved()) {
                request.setAttribute("errorMsg",
                        "You already have a pending dealer request. Please wait for admin review.");
                request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/dashboard.jsp")
                       .forward(request, response);
                return;
            }

            // DAO call unchanged — passes yearsExp as String to match original signature
            boolean saved = dao.submitDealerRequest(userId, companyName.trim(), yearsExpStr.trim());
            if (saved) {
                session.setAttribute("dealerSuccess",
                        "Your dealer request has been submitted and is pending admin review.");
                response.sendRedirect(request.getContextPath() + "/userdashboard");
            } else {
                request.setAttribute("errorMsg", "Failed to submit request. Please try again.");
                request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/become-dealer.jsp")
                       .forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "Server error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/become-dealer.jsp")
                   .forward(request, response);
        }
    }
}