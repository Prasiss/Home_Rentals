package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.HomeRental.model.UserModel;
import com.HomeRental.service.LoginService;
import com.HomeRental.utils.SessionUtil;
import com.HomeRental.utils.ValidationUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/client/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            if (!ValidationUtil.isValidString(username) || !ValidationUtil.isValidString(password)) {
                request.setAttribute("error", "Username and Password are required");
                request.getRequestDispatcher("/WEB-INF/pages/client/login.jsp").forward(request, response);
                return;
            }

            LoginService login = new LoginService();
            UserModel user = login.authenticate(username, password);

            if (user != null) {
                String role = user.getRole();

                boolean activeUser = login.isActive(user.getUserId());
                if (!activeUser) {
                    request.setAttribute("error", "Your account has not been activated by admin.");
                    request.getRequestDispatcher("/WEB-INF/pages/client/login.jsp").forward(request, response);
                    return;
                }

                SessionUtil.setAttribute(request, "username", user.getUserName(), 3600);
                SessionUtil.setAttribute(request, "userId", user.getUserId(), 3600);
                SessionUtil.setAttribute(request, "role", role, 3600);
                System.out.println(SessionUtil.getAttribute(request, "username"));
                System.out.println(SessionUtil.getAttribute(request, "role"));
             
                if ("ADMIN".equalsIgnoreCase(role)) {
                    response.sendRedirect(request.getContextPath() + "/admindashboard");
                } else if ("DEALER".equalsIgnoreCase(role)) {
                    response.sendRedirect(request.getContextPath() + "/dealerdashboard");
                } else {
                    response.sendRedirect(request.getContextPath() + "/userdashboard");
                }

            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/WEB-INF/pages/client/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Server error occurred. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/client/login.jsp").forward(request, response);
        }
    }
}
