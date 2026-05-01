package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.HomeRentals.model.UserModel;
import com.HomeRentals.service.LoginService;
import com.HomeRentals.utils.ValidationUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            String role = (String) session.getAttribute("role");
            if ("ADMIN".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else if ("DEALER".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/dealer/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/userdashboard");
            }
            return;
        }

        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!ValidationUtil.isValidString(username) || !ValidationUtil.isValidString(password)) {
            request.setAttribute("error", "Username and password are required.");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            return;
        }

        LoginService loginService = new LoginService();

        try {
            UserModel user = loginService.authenticate(username, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());

                if ("ADMIN".equals(user.getRole())) {
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                } else if ("DEALER".equals(user.getRole())) {
                    response.sendRedirect(request.getContextPath() + "/dealer/dashboard");
                } else {
                    response.sendRedirect(request.getContextPath() + "/userdashboard");
                }

            } else {
                request.setAttribute("error", "Invalid username or password.");
                request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            }

        } catch (LoginService.PendingAccountException e) {
            request.setAttribute("warning", e.getMessage());
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);

        } catch (LoginService.SuspendedAccountException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "A server error occurred. Please try again.");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
    }
}
