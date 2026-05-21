package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.HomeRental.dao.HomeRentalDAO;

@WebServlet(asyncSupported = true, urlPatterns = { "/wishlist" })
public class WishlistController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Loads the wishlist page with the user's saved properties.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            int userId = (int) session.getAttribute("userId");
            HomeRentalDAO dao = new HomeRentalDAO();
            request.setAttribute("wishlist", dao.getWishlistByUser(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/pages/client/wishList.jsp")
               .forward(request, response);
    }

    /**
     * Handles add and remove actions from the wishlist form.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            String action = request.getParameter("action");
            int propertyId = Integer.parseInt(request.getParameter("id"));
            int userId = (int) session.getAttribute("userId");

            HomeRentalDAO dao = new HomeRentalDAO();

            if ("add".equals(action)) {
                dao.addToWishlist(userId, propertyId);
                response.sendRedirect(request.getContextPath() + "/home");
            } else if ("remove".equals(action)) {
                dao.removeFromWishlist(userId, propertyId);
                response.sendRedirect(request.getContextPath() + "/wishlist");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Always redirect — never leave the response empty
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}