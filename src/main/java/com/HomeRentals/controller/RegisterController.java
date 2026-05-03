package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.HomeRentals.dao.HomeRentalsDAO;

@WebServlet(asyncSupported = true, urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName        = trim(request.getParameter("fullname"));
        String username        = trim(request.getParameter("username"));
        String email           = trim(request.getParameter("email"));
        String number          = trim(request.getParameter("number"));
        String password        = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");

        // ── Server-side validation (replaces all JS/HTML5 validation) ─────────
        String error = null;

        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty()
                || number.isEmpty() || password == null || password.isEmpty()) {
            error = "All fields are required.";
        } else if (username.length() < 3) {
            error = "Username must be at least 3 characters.";
        } else if (!email.contains("@") || !email.contains(".")) {
            error = "Please enter a valid email address.";
        } else if (!number.matches("\\d{7,15}")) {
            error = "Phone number must be 7–15 digits.";
        } else if (password.length() < 6) {
            error = "Password must be at least 6 characters.";
        } else if (!password.equals(confirmPassword)) {
            error = "Passwords do not match.";
        }

        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            return;
        }

        try {
            HomeRentalsDAO dao = new HomeRentalsDAO();
            boolean success = dao.insertUser(fullName, username, email, number, password);

            if (success) {
                HttpSession session = request.getSession();
                session.setAttribute("registerSuccess",
                    "Registration successful! Your account is pending admin approval.");
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                request.setAttribute("error", "Username or email is already in use.");
                request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Server error. Please try again.");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        }
    }

    private String trim(String s) { return s == null ? "" : s.trim(); }
}
