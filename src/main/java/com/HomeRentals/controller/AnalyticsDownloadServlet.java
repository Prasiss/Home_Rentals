package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.HomeRental.dao.HomeRentalDAO;

@WebServlet(urlPatterns = { "/admin/analytics/download" })
public class AnalyticsDownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ── Auth guard ────────────────────────────────────────────────
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        //  Load data from DAO
        HomeRentalDAO dao = new HomeRentalDAO();
        try {
            long totalRevenue      = dao.getTotalRevenue();
            int  totalUsers        = dao.getTotalUsersCount();
            int  totalDealers      = dao.getActiveDealersCount();
            int  activeProperties  = dao.getActivePropertiesCount();
            int  totalBookings     = dao.getTotalBookingsCount();
            int  completedBookings = dao.getCompletedBookingsCount();
            long avgRevenue        = completedBookings > 0 ? totalRevenue / completedBookings : 0;
            int  totalRegularUsers = dao.getTotalRegularUsersCount();

            List<String[]> monthlyRevenue    = dao.getMonthlyRevenue();
            List<String[]> monthlyBookings   = dao.getMonthlyBookingCount();
            List<String[]> monthlyUsers      = dao.getMonthlyUserRegistrations();
            List<String[]> monthlyProperties = dao.getMonthlyPropertyRegistrations();
            List<String[]> bookingStatus     = dao.getBookingStatusBreakdown();
            List<String[]> propertyStatus    = dao.getPropertyStatusBreakdown();
            List<String[]> topLocations      = dao.getTopLocations();

            String format = request.getParameter("format");

            if ("pdf".equalsIgnoreCase(format)) {
                // PDF: forward to print JSP
                String today = new SimpleDateFormat("dd MMM yyyy").format(new Date());
                request.setAttribute("reportDate",       today);
                request.setAttribute("totalRevenue",     totalRevenue);
                request.setAttribute("totalUsers",       totalUsers);
                request.setAttribute("totalDealers",     totalDealers);
                request.setAttribute("activeProperties", activeProperties);
                request.setAttribute("totalBookings",    totalBookings);
                request.setAttribute("completedBookings",completedBookings);
                request.setAttribute("avgRevenue",       avgRevenue);
                request.setAttribute("totalRegularUsers",totalRegularUsers);
                request.setAttribute("monthlyRevenue",   monthlyRevenue);
                request.setAttribute("monthlyBookings",  monthlyBookings);
                request.setAttribute("monthlyUsers",     monthlyUsers);
                request.setAttribute("monthlyProperties",monthlyProperties);
                request.setAttribute("bookingStatus",    bookingStatus);
                request.setAttribute("propertyStatus",   propertyStatus);
                request.setAttribute("topLocations",     topLocations);

                request.getRequestDispatcher("/WEB-INF/pages/admin/analytics_print.jsp")
                       .forward(request, response);

            } else {
                String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String filename = "analytics_" + today + ".csv";
                StringBuilder sb = new StringBuilder();
                sb.append('\uFEFF');

                // Section 1 – Summary
                sb.append("SECTION,METRIC,VALUE\r\n");
                sb.append("Summary,Total Revenue ($),").append(totalRevenue).append("\r\n");
                sb.append("Summary,Avg Revenue Per Booking ($),").append(avgRevenue).append("\r\n");
                sb.append("Summary,Total Users,").append(totalUsers).append("\r\n");
                sb.append("Summary,Regular Users,").append(totalRegularUsers).append("\r\n");
                sb.append("Summary,Active Dealers,").append(totalDealers).append("\r\n");
                sb.append("Summary,Active Properties,").append(activeProperties).append("\r\n");
                sb.append("Summary,Total Bookings,").append(totalBookings).append("\r\n");
                sb.append("Summary,Completed Bookings,").append(completedBookings).append("\r\n");
                sb.append("\r\n");

                // Section 2 – Monthly Revenue
                sb.append("Monthly Revenue,Month,Revenue ($)\r\n");
                for (String[] row : monthlyRevenue) {
                    sb.append("Monthly Revenue,").append(cell(row[0])).append(",").append(row[1]).append("\r\n");
                }
                sb.append("\r\n");

                // Section 3 – Monthly Bookings
                sb.append("Monthly Bookings,Month,Bookings\r\n");
                for (String[] row : monthlyBookings) {
                    sb.append("Monthly Bookings,").append(cell(row[0])).append(",").append(row[1]).append("\r\n");
                }
                sb.append("\r\n");

                // Section 4 – Monthly Users
                sb.append("Monthly Users,Month,New Users\r\n");
                for (String[] row : monthlyUsers) {
                    sb.append("Monthly Users,").append(cell(row[0])).append(",").append(row[1]).append("\r\n");
                }
                sb.append("\r\n");

                // Section 5 – Monthly Properties
                sb.append("Monthly Properties,Month,Properties Listed\r\n");
                for (String[] row : monthlyProperties) {
                    sb.append("Monthly Properties,").append(cell(row[0])).append(",").append(row[1]).append("\r\n");
                }
                sb.append("\r\n");

                // Section 6 – Booking Status
                sb.append("Booking Status,Status,Count\r\n");
                for (String[] row : bookingStatus) {
                    sb.append("Booking Status,").append(cell(row[0])).append(",").append(row[1]).append("\r\n");
                }
                sb.append("\r\n");

                // Section 7 – Property Status
                sb.append("Property Status,Status,Count\r\n");
                for (String[] row : propertyStatus) {
                    sb.append("Property Status,").append(cell(row[0])).append(",").append(row[1]).append("\r\n");
                }
                sb.append("\r\n");

                // Section 8 – Top Locations
                sb.append("Top Locations,Location,Listings\r\n");
                for (String[] row : topLocations) {
                    sb.append("Top Locations,").append(cell(row[0])).append(",").append(row[1]).append("\r\n");
                }

                byte[] bytes = sb.toString().getBytes("UTF-8");

                response.setContentType("text/csv; charset=UTF-8");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
                response.setContentLength(bytes.length);

                OutputStream out = response.getOutputStream();
                out.write(bytes);
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/analytics");
        }
    }

    private String cell(String value) {
        if (value == null) return "";
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
