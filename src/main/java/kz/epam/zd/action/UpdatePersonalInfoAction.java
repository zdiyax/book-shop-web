package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.model.user.User;
import kz.epam.zd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.*;

public class UpdatePersonalInfoAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(UpdatePersonalInfoAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        UserService userService = new UserService();
        User user = (User) req.getSession().getAttribute(USER);

        user.setFullName(req.getParameter(FULL_NAME));
        user.setEmail(req.getParameter(EMAIL));
        user.setAddress(req.getParameter(ADDRESS));
        user.setTelephoneNumber(req.getParameter(TELEPHONE_NUMBER));

        User updatedUser = user;
        try {
            updatedUser = userService.updatePersonalInfo(user);
        } catch (ServiceException e) {
            log.debug("Error in UpdatePersonalInfoAction.class occurred");
        }
        req.getSession().setAttribute(USER, updatedUser);
        String referer = req.getHeader(REFERER);

        return REDIRECT_PREFIX + referer;
    }
}
