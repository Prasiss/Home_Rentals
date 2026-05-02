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
        try {
            int userId = (int) session.getAttribute("user_id");
            if ("updateProfile".equals(action)) {
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String image = handleImageUpload(request);
                dao.updateAdminProfile(userId, fullName, email, phone, address, image);
            } else if ("changePassword".equals(action)) {
                dao.updatePassword(userId, request.getParameter("newPassword"));
            }
            response.sendRedirect(request.getContextPath() + "/admin/profile");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/profile");
        }
    }

    private String handleImageUpload(HttpServletRequest request) throws Exception {
        Part filePart = request.getPart("profileImage");
        if (filePart == null || filePart.getSize() == 0) return "default.png";
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads/profiles";
        new File(uploadPath).mkdirs();
        String name = UUID.randomUUID().toString() + ".jpg";
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, new File(uploadPath, name).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return name;
    }
}