package next.controller;

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
 * Created by stripes on 2017. 2. 21..
 */
@WebServlet(name = "dispatcher", urlPatterns  = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String PREFIX_REDIRECT = "redirect:";
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMapping rm;

    @Override
    public void init() {
        log.debug("init() in dispatcher class process");
        rm = new RequestMapping();
        rm.initMapping();

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller c = rm.getController(req.getRequestURI());
        log.debug("Controller check : {}", c);
        try {
            String returnPath = c.execute(req, resp);
            log.debug("returnPath : {}", returnPath);
            if (returnPath.startsWith(PREFIX_REDIRECT)) {
                resp.sendRedirect(returnPath.substring(PREFIX_REDIRECT.length()));
                return;
            }
            RequestDispatcher rd = req.getRequestDispatcher(returnPath);
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
