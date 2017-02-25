package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by stripes on 2017. 2. 21..
 */
public interface Controller {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
