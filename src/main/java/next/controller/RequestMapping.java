package next.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stripes on 2017. 2. 21..
 */
public class RequestMapping {
    private static final Logger log = LoggerFactory.getLogger(RequestMapping.class);
    private Map<String, Controller> map = new HashMap<String, Controller>();

    void initMapping() {
        map.put("/", new HomeController());
        map.put("/users/form", new ForwardController("/user/form.jsp"));
        map.put("/users", new ListUserController());
        map.put("/users/profile", new ProfileController());
        map.put("/users/logout", new LogoutController());
        map.put("/users/login", new LoginController());
        map.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        map.put("/users/create", new CreateUserController());
        map.put("/users/update", new UpdateUserController());
        map.put("/users/updateForm", new ForwardController("/user/updateForm.jsp"));

        log.debug("initMapping complete!");
    }

    public Controller getController(String path) {
        return map.get(path);
    }
}
