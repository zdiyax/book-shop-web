package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.exception.ValidatorException;
import kz.epam.zd.model.user.Locale;
import kz.epam.zd.model.user.RoleType;
import kz.epam.zd.model.user.User;
import kz.epam.zd.model.user.UserRole;
import kz.epam.zd.service.UserService;
import kz.epam.zd.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Anonymous action to register in the system.
 */
public class RegisterAction implements Action {
    private static final String REDIRECT_REGISTER_SUCCESS = "redirect:/do/?action=show-register-success-page";
    private static final String REDIRECT_REGISTER_FORM = "redirect:/do/?action=show-register-page";
    private static final String REGISTER = "register";
    private static final Logger log = LoggerFactory.getLogger(RegisterAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String username = request.getParameter(USERNAME);
        request.getSession().setAttribute(USERNAME, username);

        try {
            FormValidator validator = new FormValidator();
            Map<String, List<String>> fieldErrors = validator.validate(REGISTER, request);
            validator.checkFieldsOnEquals(PASSWORD, CONFIRM_PASSWORD, request);
            if (validator.hasFieldsErrors(request, fieldErrors)) return REDIRECT_REGISTER_FORM;
        } catch (ValidatorException e) {
            throw new ActionException(e);
        }
        log.debug("Register form is valid");

        String password = request.getParameter(PASSWORD);
        Locale locale = new Locale((String) request.getSession().getAttribute(LOCALE));
        UserRole userRole = new UserRole(RoleType.CUSTOMER);
        User user = new User(username, password, locale, userRole);
        UserService userService = new UserService();

        try {
            userService.register(user);
        } catch (ServiceException e) {
            request.getSession().setAttribute(REGISTER + FORM_ERRORS, e.getMessage());
            return REDIRECT_REGISTER_FORM;
        }
        log.debug("User registered | Username = {}", user.getUsername());
        request.getSession().setAttribute(USER, user);
        return REDIRECT_REGISTER_SUCCESS;
    }
}
