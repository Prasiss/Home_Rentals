package com.HomeRentals.controller;

import com.HomeRentals.dao.*;
import com.HomeRentals.model.User;
import com.HomeRentals.model.Property;
import com.HomeRentals.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = {"/superadmin/dashboard"})
public class SuperAdminDashboardController extends HttpServlet {

    private final UserDAO     userDAO     = new UserDAO();
    private final PropertyDAO propertyDAO = new PropertyDAO();
    private final BookingDAO  bookingDAO  = new BookingDAO();
    private final PaymentDAO  paymentDAO  = new PaymentDAO();

    private static final String VIEW = "/pages/superadmin/dashboard.jsp";

    /* ── GET ─────────────────────────────────────────────────────── */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtil.isSuperAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String tab = request.getParameter("tab");
        if (tab == null || tab.isBlank()) tab = "overview";
        request.setAttribute("activeTab", tab);

        switch (tab) {
            case "users":
                request.setAttribute("users", userDAO.getAllUsers());
                break;
            case "admins":
                request.setAttribute("admins", userDAO.getUsersByRole("admin"));
                break;
            case "dealers":
                request.setAttribute("dealers", userDAO.getUsersByRole("dealer"));
                break;
            case "properties":
                String filter = request.getParameter("filter");
                List<Property> props = (filter != null && !filter.isBlank())
                    ? propertyDAO.getPropertiesByStatus(filter)
                    : propertyDAO.getAllProperties();
                request.setAttribute("properties", props);
                request.setAttribute("filter", filter);
                break;
            case "bookings":
                request.setAttribute("bookings", bookingDAO.getAllBookings());
                break;
            default: // overview
                request.setAttribute("totalUsers",      userDAO.getTotalUsers());
                request.setAttribute("totalAdmins",     userDAO.getTotalAdmins());
                request.setAttribute("totalDealers",    userDAO.getTotalDealers());
                request.setAttribute("totalProperties", propertyDAO.getTotalProperties());
                request.setAttribute("totalBookings",   bookingDAO.getTotalBookings());
                request.setAttribute("activeBookings",  bookingDAO.getActiveBookingsCount());
                request.setAttribute("totalRevenue",    paymentDAO.getTotalRevenue());

                List<?> allBookings = bookingDAO.getAllBookings();
                request.setAttribute("recentBookings",
                    allBookings.size() > 5 ? allBookings.subList(0, 5) : allBookings);
                request.setAttribute("pendingProperties",
                    propertyDAO.getPropertiesByStatus("pending"));
                break;
        }

        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    /* ── POST ────────────────────────────────────────────────────── */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtil.isSuperAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        String base   = request.getContextPath() + "/superadmin/dashboard";

        switch (action == null ? "" : action) {
            case "createAdmin": {
                User u = buildUser(request, "admin");
                SessionUtil.setFlash(request, userDAO.createUser(u)
                    ? "Admin created successfully."
                    : "Error: username or email already exists.");
                response.sendRedirect(base + "?tab=admins"); break;
            }
            case "createUser": {
                User u = buildUser(request, request.getParameter("role"));
                SessionUtil.setFlash(request, userDAO.createUser(u)
                    ? "User created successfully."
                    : "Error: username or email already exists.");
                response.sendRedirect(base + "?tab=users"); break;
            }
            case "suspendUser":
                userDAO.updateStatus(toInt(request, "userId"), "inactive");
                SessionUtil.setFlash(request, "User suspended.");
                response.sendRedirect(base + "?tab=users"); break;

            case "restoreUser":
                userDAO.updateStatus(toInt(request, "userId"), "active");
                SessionUtil.setFlash(request, "User restored.");
                response.sendRedirect(base + "?tab=users"); break;

            case "deleteUser":
                userDAO.deleteUser(toInt(request, "userId"));
                SessionUtil.setFlash(request, "User deleted.");
                response.sendRedirect(base + "?tab=users"); break;

            case "revokeAdmin":
                userDAO.updateStatus(toInt(request, "userId"), "inactive");
                SessionUtil.setFlash(request, "Admin access revoked.");
                response.sendRedirect(base + "?tab=admins"); break;

            case "approveDealer":
                userDAO.updateStatus(toInt(request, "userId"), "active");
                SessionUtil.setFlash(request, "Dealer approved.");
                response.sendRedirect(base + "?tab=dealers"); break;

            case "suspendDealer":
                userDAO.updateStatus(toInt(request, "userId"), "inactive");
                SessionUtil.setFlash(request, "Dealer suspended.");
                response.sendRedirect(base + "?tab=dealers"); break;

            case "approveProperty":
                propertyDAO.updateAvailability(toInt(request, "propertyId"), "available");
                SessionUtil.setFlash(request, "Property approved.");
                response.sendRedirect(base + "?tab=properties"); break;

            case "deleteProperty":
                propertyDAO.deleteProperty(toInt(request, "propertyId"));
                SessionUtil.setFlash(request, "Property removed.");
                response.sendRedirect(base + "?tab=properties"); break;

            case "confirmBooking":
                bookingDAO.updateStatus(toInt(request, "bookingId"), "confirmed");
                SessionUtil.setFlash(request, "Booking confirmed.");
                response.sendRedirect(base + "?tab=bookings"); break;

            case "cancelBooking":
                bookingDAO.updateStatus(toInt(request, "bookingId"), "cancelled");
                SessionUtil.setFlash(request, "Booking cancelled.");
                response.sendRedirect(base + "?tab=bookings"); break;

            default:
                response.sendRedirect(base);
        }
    }

    private User buildUser(HttpServletRequest req, String role) {
        User u = new User();
        u.setFullName(req.getParameter("fullName"));
        u.setUsername(req.getParameter("username"));
        u.setEmail(req.getParameter("email"));
        u.setPhone(req.getParameter("phone"));
        u.setPassword(req.getParameter("password"));
        u.setRole(role != null ? role : "user");
        return u;
    }

    private int toInt(HttpServletRequest req, String param) {
        try { return Integer.parseInt(req.getParameter(param)); }
        catch (NumberFormatException e) { return -1; }
    }
}
