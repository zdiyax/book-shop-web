package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.service.UserService;
import kz.epam.zd.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {

    private static final String REDIRECT_LOGIN_FORM = "redirect:/do/?action=show-login-page";
    private static final String REDIRECT_LOGIN_SUCCESS = "redirect:/do/?action=show-login-success-page";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);

        User user = new User(login, password);
        UserService userService = new UserService();
        User foundUser;
        try {
            foundUser = userService.login(user);
        } catch (ServiceException e) {
            return REDIRECT_LOGIN_FORM;
        }

        //save user to session
        req.getSession().setAttribute(USER, foundUser);
        return REDIRECT_LOGIN_SUCCESS;
    }
}
