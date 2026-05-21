package com.HomeRental.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Security filter that protects all /dealer/* endpoints.
 * Ensures only authenticated users with role DEALER can access.
 */
@WebFilter("/dealer/*")
public class DealerAuthFilter extends HttpFilter {

    private static final long serialVersionUID = 1L;

    /**
     * Checks session and validates dealer role before allowing access.
     *
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param chain FilterChain
     * @throws IOException if redirect fails
     * @throws ServletException if filter processing fails
     */
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = req.getSession(false);

        String role = (session != null) ? (String) session.getAttribute("role") : null;
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        boolean isDealer = session != null
                && username != null
                && "DEALER".equalsIgnoreCase(role);

        if (!isDealer) {
            res.sendRedirect(req.getContextPath() + "/login");
        } else {
            chain.doFilter(req, res);
        }
    }
}