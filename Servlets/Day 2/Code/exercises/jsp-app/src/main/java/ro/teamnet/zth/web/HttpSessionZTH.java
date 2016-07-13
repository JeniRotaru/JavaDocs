package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by user on 7/13/2016.
 */
public class HttpSessionZTH extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        if (username.equals("admin") && password.equals("admin")) {
            resp.getWriter().write("Welcome back!\n" + "Username: " + username + session.getId());
        }
        else {
            req.setAttribute("username", username);
            req.setAttribute("session", session);
            resp.sendRedirect("views/loginFail.jsp");
        }
    }
}
