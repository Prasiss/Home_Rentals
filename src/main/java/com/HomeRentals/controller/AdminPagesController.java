package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet({"/admin/revenue", "/admin/platform", "/admin/listings", 
             "/admin/users", "/admin/dealers", "/admin/dealer-applications",
             "/admin/bookings", "/admin/reviews", "/admin/moderation"})
public class AdminPagesController extends HttpServlet {
    
    private static final Map<String, String> PAGE_MAP = new HashMap<>();
    
    static {
        PAGE_MAP.put("/admin/revenue", "/pages/admin/revenue.jsp");
        PAGE_MAP.put("/admin/platform", "/pages/admin/platform.jsp");
        PAGE_MAP.put("/admin/listings", "/pages/admin/listing-review.jsp");
        PAGE_MAP.put("/admin/users", "/pages/admin/users.jsp");
        PAGE_MAP.put("/admin/dealers", "/pages/admin/dealers.jsp");
        PAGE_MAP.put("/admin/dealer-applications", "/pages/admin/dealer-applications.jsp");
        PAGE_MAP.put("/admin/bookings", "/pages/admin/bookings.jsp");
        PAGE_MAP.put("/admin/reviews", "/pages/admin/reviews.jsp");
        PAGE_MAP.put("/admin/moderation", "/pages/admin/moderation.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String path = request.getServletPath();
        String jspPath = PAGE_MAP.getOrDefault(path, "/pages/admin/dashboard.jsp");
        request.getRequestDispatcher(jspPath).forward(request, response);
    }
}