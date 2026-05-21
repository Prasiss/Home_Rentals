package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Entry point /dealerdashboard — LoginController redirects DEALER here after login.
 * Forwards internally to /dealer/dashboard handled by DealerDashboardServlet.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/dealerdashboard" })
public class DealerDashboardRedirect extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/dealer/dashboard").forward(request, response);
    }
}
