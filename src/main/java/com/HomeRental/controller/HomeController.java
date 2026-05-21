package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.HomeRental.dao.HomeRentalDAO;
import com.HomeRental.model.HomeModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/home" })
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HomeController() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HomeRentalDAO dao = new HomeRentalDAO();
        
        try {
            List<HomeModel> homes = dao.getAllHomes();
            request.setAttribute("homes", homes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/pages/client/home.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HomeRentalDAO dao = new HomeRentalDAO();
        try {
            String priceParam = request.getParameter("price");
            List<HomeModel> homes;
            if (priceParam != null && !priceParam.isEmpty()) {
                int price = Integer.parseInt(priceParam);
                homes = dao.getHomesBelowPrice(price);
            } else {
                homes = dao.getAllHomes();
            }
            request.setAttribute("homes", homes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/pages/client/home.jsp")
               .forward(request, response);
    }

}
