package next.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by stripes on 2017. 2. 21..
 */
public class ForwardController implements Controller {
    private String forwardUrl;

    public ForwardController(String url) {
        if (url == null) {
            throw new NullPointerException("지정된 url이 없습니다.");
        }
        this.forwardUrl = url;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return forwardUrl;
    }
}
