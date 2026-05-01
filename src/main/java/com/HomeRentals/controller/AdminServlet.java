package com.HomeRentals.controller;

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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.HomeRentals.dao.HomeRentalsDAO;
import com.HomeRentals.model.UserModel;

@WebServlet({"/admin", "/admin/dashboard", "/admin/users", "/admin/dealers",
             "/admin/properties", "/admin/profile"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,      // 1 MB — buffer to disk above this
    maxFileSize       = 5 * 1024 * 1024,  // 5 MB max per file
    maxRequestSize    = 10 * 1024 * 1024  // 10 MB max total request
)
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR = "uploads" + File.separator + "profiles";

    private HomeRentalsDAO dao;

    public void init() throws ServletException {
        dao = new HomeRentalsDAO();
    }

    // ──────────────────────────── GET ────────────────────────────

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String path = request.getServletPath();

        try {
            if (path.contains("users")) {
                List<UserModel> userList = dao.getAllUsers();
                request.setAttribute("userList", userList);
                request.getRequestDispatcher("/WEB-INF/views/admin/users.jsp").forward(request, response);

            } else if (path.contains("dealers")) {
                List<UserModel> dealerList = dao.getAllDealers();
                List<UserModel> dealerRequests = dao.getPendingDealerRequests();
                request.setAttribute("dealerList", dealerList);
                request.setAttribute("dealerRequests", dealerRequests);
                request.getRequestDispatcher("/WEB-INF/views/admin/dealers.jsp").forward(request, response);

            } else if (path.contains("properties")) {
                String filterStatus = request.getParameter("status");
                List<Map<String, Object>> propertyList;
                if (filterStatus != null && !filterStatus.isEmpty()) {
                    propertyList = dao.getPropertiesByStatus(filterStatus);
                } else {
                    propertyList = dao.getPendingProperties();
                    filterStatus = "PENDING";
                }
                request.setAttribute("propertyList", propertyList);
                request.setAttribute("currentFilter", filterStatus);
                request.getRequestDispatcher("/WEB-INF/views/admin/properties.jsp").forward(request, response);

            } else if (path.contains("profile")) {
                UserModel loggedInUser = (UserModel) session.getAttribute("user");
                UserModel adminProfile = dao.getAdminProfile(loggedInUser.getUserNo());
                request.setAttribute("adminProfile", adminProfile);
                request.getRequestDispatcher("/WEB-INF/views/admin/profile.jsp").forward(request, response);

            } else {
                // Dashboard
                Map<String, Object> stats = dao.getDashboardStats();
                request.setAttribute("stats", stats);
                List<UserModel> recentUsers = dao.getRecentUsers(5);
                request.setAttribute("recentUsers", recentUsers);
                List<Map<String, Object>> pendingProperties = dao.getPendingProperties();
                request.setAttribute("pendingProperties", pendingProperties);
                request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // ✅ Forward with error — do NOT redirect back to /admin/dashboard (causes infinite loop)
            request.setAttribute("errorMessage", "Error loading page: " + e.getMessage());
            try {
                request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
            } catch (Exception ex) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
    }

    // ──────────────────────────── POST ────────────────────────────

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        try {
            switch (action == null ? "" : action) {

                // ── User approvals ──────────────────────────
                case "approveUser": {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    dao.approveUser(userId);
                    response.sendRedirect(request.getContextPath() + "/admin/users");
                    return;
                }
                case "deleteUser": {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    dao.softDeleteUser(userId);
                    response.sendRedirect(request.getContextPath() + "/admin/users");
                    return;
                }

                // ── Dealer list actions ─────────────────────
                case "approveDealer": {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    dao.approveDealer(userId);
                    response.sendRedirect(request.getContextPath() + "/admin/dealers");
                    return;
                }
                case "deleteDealer": {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    dao.softDeleteDealer(userId);
                    response.sendRedirect(request.getContextPath() + "/admin/dealers");
                    return;
                }

                // ── Dealer requests (user applied to be dealer) ─
                case "approveDealerRequest": {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    dao.approveDealerRequest(userId);
                    response.sendRedirect(request.getContextPath() + "/admin/dealers");
                    return;
                }
                case "rejectDealerRequest": {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    dao.rejectDealerRequest(userId);
                    response.sendRedirect(request.getContextPath() + "/admin/dealers");
                    return;
                }

                // ── Property approvals ──────────────────────
                case "approveProperty": {
                    int propertyId = Integer.parseInt(request.getParameter("propertyId"));
                    UserModel admin = (UserModel) session.getAttribute("user");
                    dao.updatePropertyStatus(propertyId, "APPROVED", admin.getUserNo());
                    response.sendRedirect(request.getContextPath() + "/admin/properties");
                    return;
                }
                case "rejectProperty": {
                    int propertyId = Integer.parseInt(request.getParameter("propertyId"));
                    UserModel admin = (UserModel) session.getAttribute("user");
                    dao.updatePropertyStatus(propertyId, "REJECTED", admin.getUserNo());
                    response.sendRedirect(request.getContextPath() + "/admin/properties");
                    return;
                }

                // ── Admin profile update (with image upload) ─
                case "updateProfile": {
                    String fullName = request.getParameter("fullName");
                    String email    = request.getParameter("email");
                    String phone    = request.getParameter("phone");
                    String address  = request.getParameter("address");

                    UserModel loggedInUser = (UserModel) session.getAttribute("user");
                    String currentImage = loggedInUser.getProfileImage();
                    String profileImage = handleImageUpload(request, currentImage);

                    dao.updateAdminProfile(loggedInUser.getUserNo(), fullName, email, phone, address, profileImage);

                    // Refresh session user object
                    loggedInUser.setFullName(fullName);
                    loggedInUser.setEmail(email);
                    loggedInUser.setProfileImage(profileImage);
                    session.setAttribute("user", loggedInUser);

                    response.sendRedirect(request.getContextPath() + "/admin/profile");
                    return;
                }

                // ── Password change ──────────────────────────
                case "changePassword": {
                    String newPass = request.getParameter("newPassword");
                    UserModel admin = (UserModel) session.getAttribute("user");
                    dao.updatePassword(admin.getUserNo(), newPass);
                    response.sendRedirect(request.getContextPath() + "/admin/profile");
                    return;
                }

                default:
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }

    // ──────────────────────────── FILE UPLOAD HELPER ────────────────────────────

    /**
     * Saves the uploaded profile image and returns its stored filename.
     * Returns the existingImage filename unchanged if no new file was uploaded.
     */
    private String handleImageUpload(HttpServletRequest request, String existingImage)
            throws Exception {

        Part filePart = request.getPart("profileImage");

        // No file selected or empty part — keep existing image
        if (filePart == null || filePart.getSize() == 0) {
            return existingImage != null ? existingImage : "default.png";
        }

        String contentType = filePart.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            // Not an image — keep existing
            return existingImage != null ? existingImage : "default.png";
        }

        // Build absolute upload directory path inside the webapp
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Generate a unique filename to avoid collisions
        String originalName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String extension = originalName.contains(".")
                ? originalName.substring(originalName.lastIndexOf('.'))
                : ".jpg";
        String storedName = UUID.randomUUID().toString() + extension;

        // Save the file
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, new File(uploadDir, storedName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        return storedName;
    }
}
