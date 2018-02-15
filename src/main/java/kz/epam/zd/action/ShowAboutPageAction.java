package kz.epam.zd.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kz.epam.zd.util.ConstantHolder.ABOUT_PAGE;

/**
 * Global action to display About page.
 */
public class ShowAboutPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ABOUT_PAGE;
    }
}
