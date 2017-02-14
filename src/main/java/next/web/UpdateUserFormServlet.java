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
 * Created by stripes on 2017. 2. 13..
 */
@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("UpdateFormServlet start in post");
        User user = DataBase.findUserById(request.getParameter("userId"));
        User updateUser = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));
        user.updateUser(updateUser);
        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("UpdateFormServlet start in get");
        User user = DataBase.findUserById(request.getParameter("userId"));
        request.setAttribute("user", user);
        RequestDispatcher rd = request.getRequestDispatcher("/user/update.jsp");
        rd.forward(request, response);
    }
}
