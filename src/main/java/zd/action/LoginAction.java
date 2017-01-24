package zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        return null;
    }
}

