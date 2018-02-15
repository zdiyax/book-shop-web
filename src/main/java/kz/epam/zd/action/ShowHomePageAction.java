package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.HOME_PAGE;

/**
 * Global action to display Home page.
 */
public class ShowHomePageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return HOME_PAGE;
    }
}
