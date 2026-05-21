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
import com.HomeRental.model.UserModel;

@WebServlet(asyncSupported = true, urlPatterns ={"/admin/dealers"})
public class AdminDealerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HomeRentalDAO dao;

    public void init() throws ServletException { dao = new HomeRentalDAO(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            List<UserModel> dealerList     = dao.getAllDealers();
            List<UserModel> dealerRequests = dao.getPendingDealerRequests();

            request.setAttribute("dealerList",     dealerList);
            request.setAttribute("dealerRequests", dealerRequests);
            request.setAttribute("activePage",     "dealers");
            request.setAttribute("pageTitle",      "Manage Dealers");

            request.getRequestDispatcher("/WEB-INF/pages/admin/dealers.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("AdminDealerController.doGet failed: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));

            if ("approveDealer".equals(action)) {
                dao.approveDealer(userId);                  // sets status = ACTIVE for role_id = 2
            } else if ("deleteDealer".equals(action)) {
                dao.deactivateDealer(userId);               // was softDeleteDealer() � use deactivateDealer()
            } else if ("approveDealerRequest".equals(action)) {
                dao.approveDealerRequest(userId);           // sets role_id = 2, dealer_request = APPROVED
            } else if ("rejectDealerRequest".equals(action)) {
                dao.rejectDealerRequest(userId);            // sets dealer_request = REJECTED
            }

            response.sendRedirect(request.getContextPath() + "/admin/dealers");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/dealers");
        }
    }
}
