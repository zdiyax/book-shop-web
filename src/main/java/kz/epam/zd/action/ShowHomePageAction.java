package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Global action to display Home page.
 */
public class ShowHomePageAction implements Action {

    private static final String HOME_PAGE = "index";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return HOME_PAGE;
    }
}
