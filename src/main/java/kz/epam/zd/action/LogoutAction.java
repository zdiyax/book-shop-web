package kz.epam.zd.action;

import kz.epam.zd.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.USER;

public class LogoutAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(LogoutAction.class);
    private static final String REDIRECT_LOGIN_FORM = "redirect:/do/?action=show-home-page";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        if (req.getSession().getAttribute(USER) != null) {
            final User user = (User) req.getSession().getAttribute(USER);
            log.debug("User logged out | Username = {}", user.getUsername());
            req.getSession().invalidate();
        }
        return REDIRECT_LOGIN_FORM;
    }
}
