package com.HomeRental.controller;

import com.HomeRental.dao.HomeRentalDAO;
import com.HomeRental.model.HomeModel;
import com.HomeRental.utils.CookieUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

/**
 * Servlet implementation class WishlistServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/wishlist"})
public class WishlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
	public WishlistController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");

        String cookieValue = CookieUtil.getCookieValue(request, "wishlist_" + userId);

        List<HomeModel> wishlist = new ArrayList<>();

        try {
            HomeRentalDAO dao = new HomeRentalDAO();

            if (cookieValue != null && !cookieValue.isEmpty()) {

                String[] ids = cookieValue.split(",");

                for (String idStr : ids) {
                    if (!idStr.trim().isEmpty()) {

                        int propertyId = Integer.parseInt(idStr.trim());

                        //USING YOUR EXISTING DAO METHOD
                        HomeModel home = dao.getHomeById(propertyId);

                        if (home != null) {
                            wishlist.add(home);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("wishlist", wishlist);

        request.getRequestDispatcher("/WEB-INF/pages/client/wishList.jsp")
                .forward(request, response);
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int propertyId = Integer.parseInt(request.getParameter("id"));
            String action = request.getParameter("action");

            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("userId") == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            int userId = (Integer) session.getAttribute("userId");

            HomeRentalDAO dao = new HomeRentalDAO();

            String cookieName = "wishlist_" + userId;
            String cookieValue = CookieUtil.getCookieValue(request, cookieName);

            Set<String> set = new LinkedHashSet<>();

            if (cookieValue != null && !cookieValue.isEmpty()) {
                set.addAll(Arrays.asList(cookieValue.split(",")));
            }

            if ("add".equals(action)) {

                dao.addToWishlist(userId, propertyId);
                set.add(String.valueOf(propertyId));

            } else if ("remove".equals(action)) {

                dao.removeFromWishlist(userId, propertyId);
                set.remove(String.valueOf(propertyId));
            }

            CookieUtil.addCookie(
                    response,
                    cookieName,
                    String.join(",", set),
                    7 * 24 * 60 * 60
            );

            response.sendRedirect(request.getContextPath() + "/wishlist");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}