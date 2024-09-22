package vn.loh.controllers;

import java.io.IOException;

import vn.loh.ultils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.loh.models.UserModel; // Import UserModel
import vn.loh.services.IUserService; // Import IUserService
import vn.loh.services.impl.UserServiceImpl; // Import UserServiceImpl

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
	private IUserService userService = new UserServiceImpl(); // Initialize user service

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			resp.sendRedirect(req.getContextPath() + "/admin");
			return;
		}
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/admin");
					return;
				}
			}
		}
		req.getRequestDispatcher(Constant.Path).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		// Validate input
		if (username == null || password == null || email == null || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
			req.setAttribute("alert", "All fields are required.");
			req.getRequestDispatcher(Constant.Path).forward(req, resp);
			return;
		}

		// Check if username already exists
		if (userService.findByUsername(username) != null) {
			req.setAttribute("alert", "Username already exists.");
			req.getRequestDispatcher(Constant.Path).forward(req, resp);
			return;
		}

		// Create new user
		UserModel newUser = new UserModel();
		newUser.setUsername(username);
		newUser.setPassword(password); // Consider hashing the password
		newUser.setEmail(email);

		// Save user to the database
		userService.insert(newUser);

		// Redirect to login or success page
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}