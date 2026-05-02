package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.HomeRentals.dao.HomeRentalsDAO;
import com.HomeRentals.model.User;

@WebServlet("/admin/dealers")
public class AdminDealerServlet extends HttpServlet {
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
            List<User> dealerList = dao.getAllDealers();
            List<User> dealerRequests = dao.getPendingDealerRequests();
            request.setAttribute("dealerList", dealerList);
            request.setAttribute("dealerRequests", dealerRequests);
            request.setAttribute("activePage", "dealers");
            request.setAttribute("pageTitle", "Manage Dealers");
            request.getRequestDispatcher("/WEB-INF/views/admin/dealers.jsp").forward(request, response);
        } catch (Exception e) {
            // Rethrow so the error is visible in browser during development.
            // Replace with a redirect once the real cause is fixed.
            throw new ServletException("AdminDealerServlet.doGet failed: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            if ("approveDealer".equals(action)) dao.approveDealer(userId);
            else if ("deleteDealer".equals(action)) dao.softDeleteDealer(userId);
            else if ("approveDealerRequest".equals(action)) dao.approveDealerRequest(userId);
            else if ("rejectDealerRequest".equals(action)) dao.rejectDealerRequest(userId);
            response.sendRedirect(request.getContextPath() + "/admin/dealers");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/dealers");
        }
    }
}
