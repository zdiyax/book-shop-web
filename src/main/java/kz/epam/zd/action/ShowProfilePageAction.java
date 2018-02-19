package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Customer action to display Profile page.
 */
public class ShowProfilePageAction implements Action {

    private static final String PROFILE_PAGE = "profile";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PROFILE_PAGE;
    }
}
