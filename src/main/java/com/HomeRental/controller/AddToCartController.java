package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.HomeRental.dao.HomeRentalDAO;
import com.HomeRental.model.HomeModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/addtocart" })
public class AddToCartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddToCartController() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/client/addToCart.jsp").forward(request, response);
    } // closing brace was missing

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HomeRentalDAO dao = new HomeRentalDAO();
        HomeModel home = null;
        try {
            home = dao.getHomeById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        List<HomeModel> cart = (List<HomeModel>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        if (home != null) {
            cart.add(home);
        }
        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
