package kz.epam.zd.action;

import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Updates user's personal info in the database.
 */
public class UpdatePersonalInfoAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(UpdatePersonalInfoAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserService();
        User user = (User) request.getSession().getAttribute(USER);

        user.setFullName(request.getParameter(FULL_NAME));
        user.setEmail(request.getParameter(EMAIL));
        user.setAddress(request.getParameter(ADDRESS));
        user.setTelephoneNumber(request.getParameter(TELEPHONE_NUMBER));

        User updatedUser = user;
        try {
            updatedUser = userService.updatePersonalInfo(user);
        } catch (ServiceException e) {
            log.debug("Error in UpdatePersonalInfoAction.class occurred: {}", e.getMessage());
        }
        request.getSession().setAttribute(USER, updatedUser);
        String referer = request.getHeader(REFERER);

        return REDIRECT_PREFIX + referer;
    }
}
