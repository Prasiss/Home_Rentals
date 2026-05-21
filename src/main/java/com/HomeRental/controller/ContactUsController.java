package com.HomeRental.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.HomeRental.dao.HomeRentalDAO;

/**
 * Servlet implementation class ContactUsController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contactus" })
public class ContactUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactUsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/client/contactus.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HomeRentalDAO dao= new HomeRentalDAO(); 
			String name = request.getParameter("fullname");
			String email =request.getParameter("email"); 
			String message =request.getParameter("message");
			
			if (name == null || email == null || message == null ||
				    name.trim().isEmpty() || email.trim().isEmpty() || message.trim().isEmpty()) {

				    request.setAttribute("error", "Please fill all the fields");
				    request.getRequestDispatcher("/WEB-INF/pages/client/contactus.jsp")
				            .forward(request, response);
				    return;
				}
			boolean isSaved = dao.saveMessage(name, email, message);
			if (isSaved) {
                request.setAttribute("success", "Your message has been sent successfully!");
            } else {
                request.setAttribute("error", "Failed to send message. Try again.");
            }
            request.getRequestDispatcher("/WEB-INF/pages/client/contactus.jsp")
                    .forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error: " + e.getMessage());
		}
	}

}
