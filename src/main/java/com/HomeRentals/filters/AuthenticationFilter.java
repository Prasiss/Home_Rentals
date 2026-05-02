package com.HomeRentals.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/admin/*", "/dashboard", "/explore", "/bookings", 
            "/wishlist", "/profile", "/become-dealer"})
public class AuthenticationFilter extends HttpFilter implements Filter {

    private static final long serialVersionUID = 1L;

    public AuthenticationFilter() { super(); }
    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        // ALLOW everything without checking - prevent loops
        if (path.startsWith("/login") || 
            path.startsWith("/register") ||
            path.startsWith("/css") || 
            path.startsWith("/js") ||
            path.startsWith("/image") || 
            path.startsWith("/pages") ||
            path.endsWith(".css") || 
            path.endsWith(".js") ||
            path.endsWith(".png") || 
            path.endsWith(".jpg") ||
            path.endsWith(".jsp")) {
            chain.doFilter(request, response);
            return;
        }

        // check if logged in
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // admin check
        String role = (String) session.getAttribute("role_name");
        if (path.startsWith("/admin") && !"ADMIN".equalsIgnoreCase(role)) {
            res.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {}
}