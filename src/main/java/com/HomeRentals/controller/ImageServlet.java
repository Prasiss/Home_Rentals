package com.HomeRentals.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/getImage" })
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = System.getProperty("user.home") + File.separator + "images";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParameter = request.getParameter("id");
		if (idParameter == null || idParameter.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing name parameter");
            return;
        }
		int id = Integer.parseInt(idParameter);

        File folder = new File(UPLOAD_DIR);
        if (!folder.exists() || !folder.isDirectory()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Storage directory not found");
            return;
        }
        // 1. Search for the unique file
        // Since 'name' is unique, we look for the first file that starts with "name."
        File[] matches = folder.listFiles((dir, fileName) -> fileName.startsWith(id + "."));

        // 2. Validate the match
        if (matches != null && matches.length > 0) {
            File imageFile = matches[0]; // The unique image for this user

            // 3. Determine MIME type and Set Headers
            String contentType = getServletContext().getMimeType(imageFile.getName());
            if (contentType == null) contentType = "image/jpeg"; // Fallback
            
            response.setContentType(contentType);
            response.setContentLength((int) imageFile.length());

            // 4. Stream the data
            Files.copy(imageFile.toPath(), response.getOutputStream());
        }else {
            // If no file matches the unique name, return 404
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found for user: " + id);
        }
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
