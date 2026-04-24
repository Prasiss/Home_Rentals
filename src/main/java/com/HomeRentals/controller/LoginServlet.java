package com.homerental.controller;

import com.homerental.dao.UserDao;
import com.homerental.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet({"/login", "/logout"})
public class LoginServlet extends HttpServlet {
    
    private UserDao userDao;
    
    @Override
    public void init() { userDao = new UserDao(); }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getServletPath();
        if ("/logout".equals(path)) {
            HttpSession session = req.getSession(false);
            if (session != null) session.invalidate();
            res.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userDao.authenticateUser(email, password);
        
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedInUser", user);
            session.setAttribute("userNo", user.getUserNo());
            session.setAttribute("userName", user.getFullName());
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userInitial", user.getFullName().substring(0, 1).toUpperCase());
            
            if ("ADMIN".equals(user.getRole())) res.sendRedirect(req.getContextPath() + "/admin/dashboard");
            else if ("DEALER".equals(user.getRole())) res.sendRedirect(req.getContextPath() + "/dealer/dashboard");
            else res.sendRedirect(req.getContextPath() + "/dashboard");
        } else {
            req.setAttribute("errorMessage", "Invalid email or password");
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
    }
}