package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user/list")
public class ListUserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ListUserServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("In ListUserServlet");
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User user = (User)value;
            req.setAttribute("user", user);
        }
        req.setAttribute("users", DataBase.findAll());
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("post In ListUserServlet");
    }

}
