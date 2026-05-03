package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import com.HomeRentals.dao.HomeRentalsDAO;
import com.HomeRentals.model.User;

@WebServlet("/admin/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class AdminProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HomeRentalsDAO dao;
    public void init() throws ServletException { dao = new HomeRentalsDAO(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        try {
            int userId = (int) session.getAttribute("user_id");
            User profile = dao.getAdminProfile(userId);
            request.setAttribute("adminProfile", profile);
            request.setAttribute("activePage", "profile");
            request.setAttribute("pageTitle", "Edit Profile");
            request.getRequestDispatcher("/WEB-INF/views/admin/profile.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");
        int userId = (int) session.getAttribute("user_id");

        if ("updateProfile".equals(action)) {
            String fullName = trim(request.getParameter("fullName"));
            String email    = trim(request.getParameter("email"));
            String phone    = trim(request.getParameter("phone"));
            String address  = trim(request.getParameter("address"));

            // ── Server-side validation (replaces required + type="email") ──────
            String error = null;
            if (fullName.isEmpty()) {
                error = "Full name is required.";
            } else if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
                error = "A valid email address is required.";
            } else if (phone.isEmpty()) {
                error = "Phone number is required.";
            } else if (!phone.matches("\\d{7,15}")) {
                error = "Phone number must be 7–15 digits.";
            }

            if (error != null) {
                forwardToProfile(request, response, userId, null, error);
                return;
            }

            try {
                String image = handleImageUpload(request);
                dao.updateAdminProfile(userId, fullName, email, phone, address, image);
                forwardToProfile(request, response, userId, "Profile updated successfully.", null);
            } catch (Exception e) {
                e.printStackTrace();
                forwardToProfile(request, response, userId, null, "Server error: " + e.getMessage());
            }

        } else if ("changePassword".equals(action)) {
            String newPassword     = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            String error = null;
            if (newPassword == null || newPassword.isEmpty()) {
                error = "New password is required.";
            } else if (newPassword.length() < 6) {
                error = "Password must be at least 6 characters.";
            } else if (!newPassword.equals(confirmPassword)) {
                error = "Passwords do not match.";
            }

            if (error != null) {
                forwardToProfile(request, response, userId, null, error);
                return;
            }

            try {
                dao.updatePassword(userId, newPassword);
                forwardToProfile(request, response, userId, "Password changed successfully.", null);
            } catch (Exception e) {
                e.printStackTrace();
                forwardToProfile(request, response, userId, null, "Failed to change password.");
            }
        }
    }

    private void forwardToProfile(HttpServletRequest req, HttpServletResponse res,
            int userId, String success, String error) throws ServletException, IOException {
        try {
            User profile = dao.getAdminProfile(userId);
            req.setAttribute("adminProfile", profile);
        } catch (Exception ignored) {}
        req.setAttribute("activePage", "profile");
        req.setAttribute("pageTitle", "Edit Profile");
        if (success != null) req.setAttribute("successMsg", success);
        if (error   != null) req.setAttribute("errorMsg",   error);
        req.getRequestDispatcher("/WEB-INF/views/admin/profile.jsp").forward(req, res);
    }

    private String handleImageUpload(HttpServletRequest request) throws Exception {
        Part filePart = request.getPart("profileImage");
        if (filePart == null || filePart.getSize() == 0) return null; // null = keep existing
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads/profiles";
        new File(uploadPath).mkdirs();
        String name = UUID.randomUUID().toString() + ".jpg";
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, new File(uploadPath, name).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return name;
    }

    private String trim(String s) { return s == null ? "" : s.trim(); }
}
