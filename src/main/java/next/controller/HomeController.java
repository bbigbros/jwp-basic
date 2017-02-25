package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomeController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        log.debug("HomeController process");
        req.setAttribute("users", DataBase.findAll());
        return "/index.jsp";
    }
}
