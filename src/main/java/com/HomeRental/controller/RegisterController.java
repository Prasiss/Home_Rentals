package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import com.HomeRental.service.RegisterService;
import com.HomeRental.utils.FileUploadUtil;
import com.HomeRental.utils.ValidationUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize       = 1024 * 1024 * 10,
    maxRequestSize    = 1024 * 1024 * 50
)
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /** Same directory used by ProfileController — all profile photos live here. */
    private static final String UPLOAD_DIR =
            System.getProperty("user.home") + File.separator + "profile";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/client/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName        = request.getParameter("fullname");
        String username        = request.getParameter("username");
        String email           = request.getParameter("email");
        String number          = request.getParameter("number");
        String password        = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");

        
        if (!ValidationUtil.isValidString(fullName) ||
                !ValidationUtil.isValidString(username) ||
                !ValidationUtil.isValidString(email) ||
                !ValidationUtil.isValidString(number) ||
                !ValidationUtil.isValidString(password)) {
            request.setAttribute("error", "All fields are required");
            forward(request, response); return;
        }

        if (!ValidationUtil.matchPassword(password, confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            forward(request, response); return;
        }

        if (fullName.length() < 3) {
            request.setAttribute("error", "Full name must contain at least 3 characters");
            forward(request, response); return;
        }

        if (username.length() < 4) {
            request.setAttribute("error", "Username must contain at least 4 characters");
            forward(request, response); return;
        }

        if (!ValidationUtil.validEmail(email)) {
            request.setAttribute("error", "Please enter a valid email address");
            forward(request, response); return;
        }

        if (!ValidationUtil.validPhoneNumber(number)) {
            request.setAttribute("error", "Phone number must start with 98 and contain 10 digits");
            forward(request, response); return;
        }

        if (!ValidationUtil.validPassword(password)) {
            request.setAttribute("error",
                    "Password must contain at least 8 characters, one uppercase letter, one number, and one special character");
            forward(request, response); return;
        }

        // ── Register user ─────────────────────────────────────────────────
        try {
            RegisterService registerService = new RegisterService();

            String profileImageKey = null;

            Part filePart = request.getPart("profileImage");
            if (filePart != null && filePart.getSize() > 0) {
                if (FileUploadUtil.isImage(filePart)) {
                    String ext      = FileUploadUtil.getFileExtension(filePart.getSubmittedFileName());
                    String fileName = "reg_" + username + ext;
                    FileUploadUtil.saveFile(filePart, UPLOAD_DIR, fileName);
                    profileImageKey = "reg_" + username; // stored without extension
                }
                // If not an image, silently ignore and register without photo
            }

            // Use a final variable so it can be safely used inside the lambda below
            final String finalProfileImageKey = profileImageKey;

            boolean success = registerService.addUser(fullName, username, email, number, password, finalProfileImageKey);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                // Clean up uploaded photo if DB insert failed
                if (finalProfileImageKey != null) {
                    File dir   = new File(UPLOAD_DIR);
                    File[] old = dir.listFiles((d, n) -> n.startsWith(finalProfileImageKey + "."));
                    if (old != null) { for (File f : old) f.delete(); }
                }
                request.setAttribute("error", "Registration failed. Username or email may already exist. Try again.");
                forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Server error occurred");
            forward(request, response);
        }
    }

    private void forward(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/client/register.jsp").forward(req, res);
    }
}