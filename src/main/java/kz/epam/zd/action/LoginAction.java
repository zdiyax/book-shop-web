package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.Book;
import kz.epam.zd.model.user.Locale;
import kz.epam.zd.model.user.RoleType;
import kz.epam.zd.model.user.User;
import kz.epam.zd.model.user.UserRole;
import kz.epam.zd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Anonymous action to login into system
 */
public class LoginAction implements Action {

    private static final String REDIRECT_LOGIN_FORM = "redirect:/do/?action=show-login-page";
    private static final String REDIRECT_LOGIN_SUCCESS = "redirect:/do/?action=show-login-success-page";
    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter(USERNAME);
        request.getSession().setAttribute(USERNAME, username);
        String password = request.getParameter(PASSWORD);
        String locale = (String) request.getSession().getAttribute(LOCALE);

        final User user = new User(username, password, new Locale(locale), new UserRole(RoleType.CUSTOMER));
        UserService userService = new UserService();
        User foundUser;
        try {
            foundUser = userService.login(user);
            foundUser.setLocale(new Locale(locale));
        } catch (ServiceException e) {
            request.getSession().setAttribute(LOGIN + FORM_ERRORS, e.getMessage());
            return REDIRECT_LOGIN_FORM;
        }
        log.debug("User logged in | Username = {}", foundUser.getUsername());
        request.getSession().setAttribute(USER, foundUser);

        //creates an empty cart in user session
        request.getSession().setAttribute(CART, new HashMap<Book, Integer>());

        return REDIRECT_LOGIN_SUCCESS;
    }
}

