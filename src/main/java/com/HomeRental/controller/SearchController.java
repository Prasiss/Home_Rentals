package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.HomeRental.model.HomeModel;
import com.HomeRental.service.SearchService;

@WebServlet(asyncSupported = true, urlPatterns = { "/search" })
public class SearchController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchController() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String house = request.getParameter("query");
            if (house == null || house.trim().isEmpty()) {
                request.getRequestDispatcher("/WEB-INF/pages/client/search.jsp").forward(request, response);
                return;
            }	
            SearchService service = new SearchService();
            List<HomeModel> properties = service.searchProperty(house);
            request.setAttribute("properties", properties);
            request.getRequestDispatcher("/WEB-INF/pages/client/search.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Search failed");
            request.getRequestDispatcher("/WEB-INF/pages/client/search.jsp").forward(request, response);
        } 
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    } 
}
