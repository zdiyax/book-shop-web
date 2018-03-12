package kz.epam.zd.filter;

import kz.epam.zd.model.user.RoleType;
import kz.epam.zd.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static kz.epam.zd.util.ConstantHolder.ACTION;
import static kz.epam.zd.util.ConstantHolder.USER;

/**
 * Filter implementation to manage access level and action permissions.
 */
public class AccessFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AccessFilter.class);
    private static final String ANONYMOUS_ACTION_FILE_NAME = "actions-anonymous.properties";
    private static final String CUSTOMER_ACTION_FILE_NAME = "actions-customer.properties";
    private static final String OPERATOR_ACTION_FILE_NAME = "actions-operator.properties";
    private static final String ALL_ACTION_FILE_NAME = "actions-all.properties";
    private List<String> anonymousActionList = new ArrayList<>();
    private List<String> userActionList = new ArrayList<>();
    private List<String> managerActionList = new ArrayList<>();
    private List<String> allActionList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) {

        try {
            anonymousActionList = getListFromFile(ANONYMOUS_ACTION_FILE_NAME);
            userActionList = getListFromFile(CUSTOMER_ACTION_FILE_NAME);
            managerActionList = getListFromFile(OPERATOR_ACTION_FILE_NAME);
            allActionList = getListFromFile(ALL_ACTION_FILE_NAME);
        } catch (IOException e) {
            log.error("Unable to open and load access actions from file.", e);
        }
    }

    private List<String> getListFromFile(String fileName) throws IOException {
        List<String> list = new ArrayList<>();
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }
        return list;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //read action from received request
        String actionName = req.getParameter(ACTION);

        final User user = (User) req.getSession().getAttribute(USER);

        //get list with actions for user by his role
        List<String> actionList = getActionList(user);

        //check if action consist in list with all action
        if (!allActionList.contains((actionName))) {
            //if no, send on page with 404 error
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            log.debug("The requested action {} not found", actionName);
            return;
        } else {
            //check if user has access to this action
            if (!actionList.contains(actionName)) {
                //if no, send user on page with 403 error code
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                log.debug("Not authorized attempt to application, action \"{}\" from user \"{}\"",
                        actionName, user);
                return;
            }
        }

        filterChain.doFilter(req, resp);
    }

    /**
     * Returns corresponding list of actions allowed for entered user.
     *
     * @param user user whose actions to be returned
     * @return action list
     */
    private List<String> getActionList(User user) {
        if (user == null) return anonymousActionList;
        if (RoleType.CUSTOMER.toString().equals(user.getRole().getRoleType().toString()))
            return userActionList;
        if (RoleType.OPERATOR.toString().equals(user.getRole().getRoleType().toString()))
            return managerActionList;
        return anonymousActionList;
    }

    @Override
    public void destroy() {
    }
}
