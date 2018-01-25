package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.user.Locale;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.*;

public class RegisterAction implements Action {
    private static final String REDIRECT_REGISTER_SUCCESS = "redirect:/do/?action=show-register-success";
    private static final String REDIRECT_REGISTER_FORM = "redirect:/do/?action=show-register-page";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String username = request.getParameter(USERNAME);
        request.getSession().setAttribute(USERNAME, username);

        /* Form validation here!*/


        /*                      */

        String password = request.getParameter(PASSWORD);
        Locale locale = new Locale((String) request.getSession().getAttribute(LOCALE));
        User user = new User(username, password, locale);
        UserService userService = new UserService();

        try {
            userService.register(user);
        } catch (ServiceException e) {
            return REDIRECT_REGISTER_FORM;
        }

        request.getSession().setAttribute(USER, user);
        return REDIRECT_REGISTER_SUCCESS;
    }
}
