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
import com.HomeRental.model.UserModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/homedescription" })
public class HomeDescriptionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HomeDescriptionController() { super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HomeRentalDAO dao = new HomeRentalDAO();
        String idParam = request.getParameter("id");
        HomeModel home  = null;
        UserModel owner = null;
        try {
            List<HomeModel> suggestedHomes = dao.getTop3Homes();
            request.setAttribute("suggestedHomes", suggestedHomes);
            if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam);
                home = dao.getHomeById(id);
                if (home != null) {
                    owner = dao.getOwnerByPropertyId(id);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid property ID format");
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("home", home);
        request.setAttribute("owner", owner);
        request.getRequestDispatcher("/WEB-INF/pages/client/homedescription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
} // closing brace was missing
