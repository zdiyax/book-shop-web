package zd.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.exception.ServiceException;
import zd.model.user.User;
import zd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
    private static final String REDIRECT_LOGIN_FORM = "redirect:/do/?action=show-login-page";
    private static final String REDIRECT_LOGIN_SUCCESS = "redirect:/do/?action=show-login-success";
    private static final String LOGIN_BUTTON = "loginButton";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String LOCALE = "locale";
    private static final String ERROR_MESSAGES_POSTFIX = "ErrorMessages";
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String login = req.getParameter(LOGIN);
        req.getSession().setAttribute(LOGIN, login);
        String password = req.getParameter(PASSWORD);
        String locale = (String) req.getSession().getAttribute(LOCALE);

        final User user = new User(login, password);
        UserService userService = new UserService();
        User foundUser;
        try {
            foundUser = userService.login(user);
        } catch (ServiceException e) {
            req.getSession().setAttribute(LOGIN_BUTTON + ERROR_MESSAGES_POSTFIX, e.getMessage());
            return REDIRECT_LOGIN_FORM;
        }

        //save user to session
        req.getSession().setAttribute(USER, foundUser);
        return REDIRECT_LOGIN_SUCCESS;
    }
}

