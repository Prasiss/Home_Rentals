package com.HomeRental.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({ "/userdashboard", "/admindashboard", "/dealerdashboard" })
public class AuthenticationFilter extends HttpFilter {

    private static final long serialVersionUID = 1L;

    /**
     * Filters incoming requests to protected dashboard pages.
     * Ensures user is authenticated and has correct role access.
     *
     * @param req HttpServletRequest object
     * @param res HttpServletResponse object
     * @param chain FilterChain for continuing request processing
     * @throws IOException if redirect fails
     * @throws ServletException if filter processing fails
     */
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = req.getSession(false);

        String role = (session != null) ? (String) session.getAttribute("role") : null;
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (session == null || username == null || role == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String path = req.getServletPath();

        if (path.equals("/admindashboard") && !"ADMIN".equalsIgnoreCase(role)) {
            redirectByRole(req, res, role);
            return;
        }

        if (path.equals("/dealerdashboard") && !"DEALER".equalsIgnoreCase(role)) {
            redirectByRole(req, res, role);
            return;
        }

        if (path.equals("/userdashboard") && !"USER".equalsIgnoreCase(role)) {
            redirectByRole(req, res, role);
            return;
        }

        chain.doFilter(req, res);
    }

    /**
     * Redirects user to correct dashboard based on role.
     *
     * @param req HttpServletRequest object
     * @param res HttpServletResponse object
     * @param role User role (ADMIN/DEALER/USER)
     * @throws IOException if redirect fails
     */
    private void redirectByRole(HttpServletRequest req, HttpServletResponse res, String role)
            throws IOException {

        if ("ADMIN".equalsIgnoreCase(role)) {
            res.sendRedirect(req.getContextPath() + "/admindashboard");
        } else if ("DEALER".equalsIgnoreCase(role)) {
            res.sendRedirect(req.getContextPath() + "/dealerdashboard");
        } else {
            res.sendRedirect(req.getContextPath() + "/userdashboard");
        }
    }
}