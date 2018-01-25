package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.user.Locale;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static kz.epam.zd.util.ConstantHolder.*;

public class LoginAction implements Action {
    private static final String REDIRECT_LOGIN_FORM = "redirect:/do/?action=show-login-page";
    private static final String REDIRECT_LOGIN_SUCCESS = "redirect:/do/?action=show-login-success-page";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String login = req.getParameter(LOGIN);
        req.getSession().setAttribute(LOGIN, login);
        String password = req.getParameter(PASSWORD);
        String locale = (String) req.getSession().getAttribute(LOCALE);

        final User user = new User(login, password, new Locale(locale));
        UserService userService = new UserService();
        User foundUser;
        try {
            foundUser = userService.login(user);
            foundUser.setLocale(new Locale(locale));
        } catch (ServiceException e) {
            req.getSession().setAttribute(LOGIN + FORM_ERRORS, e.getMessage());
            return REDIRECT_LOGIN_FORM;
        }

        req.getSession().setAttribute(USER, foundUser);
        return REDIRECT_LOGIN_SUCCESS;
    }
}

