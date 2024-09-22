package vn.loh.controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/forgetpass" })
public class ForgetPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		showPageForget(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		checkEmailForget(req, resp);
	}

	private void showPageForget(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/forgetpass.jsp");
		rd.forward(req, resp);
	}

	private void checkEmailForget(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String formail = req.getParameter("formail");
		if (formail != null && !formail.isEmpty()) {
			resp.sendRedirect(req.getContextPath() + "/views/changepass.jsp");
			showPageForget(req, resp);
		} else {
			req.setAttribute("exception", "Invalid email address.");
			req.setAttribute("formail", formail);
			showPageForget(req, resp);
		}
	}

}