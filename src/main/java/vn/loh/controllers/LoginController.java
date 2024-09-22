package vn.loh.controllers;

import java.io.IOException;
import java.io.Serial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.loh.models.UserModel;
import vn.loh.services.IUserService;
import vn.loh.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/login", "/dang-nhap"})
public class LoginController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        // Nhan tham so tu URL
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String alertMsg = "";

        // Xu ly logic
        if (username.isEmpty() || password.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }
        IUserService userService = new UserServiceImpl();
        UserModel user = userService.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
//            if (isRememberMe) {
//                saveRemeberMe(resp, username);
//            }
            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}

