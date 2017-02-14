package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by stripes on 2017. 2. 14..
 */
@WebServlet("/user/profile")
public class ProfileUserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ProfileUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = DataBase.findUserById(req.getParameter("userId"));

        if (user == null) {
            // 가입되어 있지 않은 회원입니다.
            resp.sendRedirect("/");
            return;
        }
        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/profile.jsp");
        rd.forward(req, resp);
    }
}
