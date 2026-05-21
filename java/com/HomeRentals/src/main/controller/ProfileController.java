package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.HomeRental.model.UserModel;
import com.HomeRental.utils.FileUploadUtil;
import com.HomeRental.dao.HomeRentalDAO;

@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize       = 1024 * 1024 * 10,
    maxRequestSize    = 1024 * 1024 * 50
)
public class ProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR =
            System.getProperty("user.home") + File.separator + "profile";

    private final HomeRentalDAO dao = new HomeRentalDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        transferFlash(session, request, "successMsg");
        transferFlash(session, request, "errorMsg");

        try {
            int userId = (int) session.getAttribute("userId");
            UserModel user = dao.getUserById(userId);
            request.setAttribute("user", user);

            // Check if image exists on disk and pass the name to JSP
            File dir = new File(UPLOAD_DIR);
            if (dir.exists()) {
                File[] matches = dir.listFiles((d, n) -> n.startsWith("user_" + userId + "."));
                if (matches != null && matches.length > 0) {
                    // Pass filename without extension
                    request.setAttribute("profileImageName", "user_" + userId);
                }
            }

            String role = (String) session.getAttribute("role");
            if ("DEALER".equalsIgnoreCase(role)) {
                request.getRequestDispatcher("/WEB-INF/pages/dealer/profile.jsp")
                       .forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/pages/client/dashboard/profile.jsp")
                       .forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            String role = (String) session.getAttribute("role");
            response.sendRedirect(request.getContextPath() +
                    ("DEALER".equalsIgnoreCase(role) ? "/dealer/dashboard" : "/userdashboard"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int    userId   = (int) session.getAttribute("userId");
        String fullName = trim(request.getParameter("fullName"));
        String email    = trim(request.getParameter("email"));
        String phone    = trim(request.getParameter("number"));

        String error = null;
        if (fullName.isEmpty()) {
            error = "Full name is required.";
        } else if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            error = "A valid email address is required.";
        } else if (phone.isEmpty()) {
            error = "Phone number is required.";
        }

        if (error != null) {
            session.setAttribute("errorMsg", error);
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }

        try {
            // ── Handle image upload ───────────────────────────────────────
            Part filePart = request.getPart("profileImage");
            if (filePart != null && filePart.getSize() > 0) {
                if (FileUploadUtil.isImage(filePart)) {
                    String ext      = FileUploadUtil.getFileExtension(filePart.getSubmittedFileName());
                    String fileName = "user_" + userId + ext;

                    // Remove any old photo for this user before saving
                    File dir = new File(UPLOAD_DIR);
                    if (!dir.exists()) dir.mkdirs();
                    File[] old = dir.listFiles((d, n) -> n.startsWith("user_" + userId + "."));
                    if (old != null) { for (File f : old) f.delete(); }

                    FileUploadUtil.saveFile(filePart, UPLOAD_DIR, fileName);
                } else {
                    session.setAttribute("errorMsg", "Uploaded file is not a valid image.");
                    response.sendRedirect(request.getContextPath() + "/profile");
                    return;
                }
            }

            // ── Update text fields ────────────────────────────────────────
            dao.updateUserProfile(userId, fullName, email, phone);
            session.setAttribute("username", fullName);

            // After saving, verify the account is still active.
            // A user with a pending dealer application or a deactivated account
            // has is_approved = 0 in the DB. Surface this clearly rather than
            // leaving the user confused about why they get locked out later.
            boolean stillActive = dao.isActive(userId);
            if (!stillActive) {
                session.setAttribute("errorMsg",
                    "Your profile was saved, but your account is currently inactive or pending admin approval. "
                    + "Some features may be unavailable until an admin activates your account.");
            } else {
                session.setAttribute("successMsg", "Profile updated successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("errorMsg", "Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMsg", "Something went wrong: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/profile");
    }

    private String trim(String s) { return s == null ? "" : s.trim(); }

    private void transferFlash(HttpSession session, HttpServletRequest request, String key) {
        Object val = session.getAttribute(key);
        if (val != null) {
            request.setAttribute(key, val);
            session.removeAttribute(key);
        }
    }
}