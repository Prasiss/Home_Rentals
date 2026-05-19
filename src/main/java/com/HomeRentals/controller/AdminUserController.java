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

@WebServlet(asyncSupported = true, urlPatterns ={"/admin/users"})
public class AdminUserController extends HttpServlet {
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
            List<UserModel> userList = dao.getAllUsers();

            request.setAttribute("userList",   userList);
            request.setAttribute("activePage", "users");
            request.setAttribute("pageTitle",  "Manage Users");

            request.getRequestDispatcher("/WEB-INF/pages/admin/users.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("AdminUserServlet.doGet failed: " + e.getMessage(), e);
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

            if ("approveUser".equals(action)) {
                dao.approveUser(userId);          // sets status = ACTIVE
            } else if ("deleteUser".equals(action)) {
                dao.deleteUser(userId);            // soft delete � sets status = INACTIVE
            }

            response.sendRedirect(request.getContextPath() + "/admin/users");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }
    }
}
