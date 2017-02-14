package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by stripes on 2017. 2. 14..
 */
@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = DataBase.findUserById(request.getParameter("userId"));
        if (user == null || !user.chkPassword(request.getParameter("password"))) {
            response.sendRedirect("/user/login_failed.jsp");
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
