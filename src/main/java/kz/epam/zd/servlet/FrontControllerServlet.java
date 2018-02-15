package kz.epam.zd.servlet;

import kz.epam.zd.action.Action;
import kz.epam.zd.action.ActionFactory;
import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.ActionFactoryException;
import kz.epam.zd.util.ValidatorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Main HttpServlet operating with actions.
 */
@WebServlet(name = "FrontControllerServlet", urlPatterns = "/do/*")
public class FrontControllerServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(FrontControllerServlet.class);
    private static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";
    private ActionFactory actionFactory;

    @Override
    public void init() {
        actionFactory = new ActionFactory();
        try {
            actionFactory.loadActions();
        } catch (ActionFactoryException e) {
            log.error("ActionFactory failed to initialize", e);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkErrorCode(request, response);
        String actionName = getActionName(request);
        try {
            Action action = actionFactory.getAction(actionName);
            String execute = action.execute(request, response);
            proceedTo(execute, request, response);
        } catch (ActionException | ActionFactoryException e) {
            log.error("Error in FrontControllerServlet occurred", e);
        }
    }

    private void proceedTo(String execute, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (execute.startsWith(REDIRECT_PREFIX)) {
            response.sendRedirect(execute.substring(REDIRECT_PREFIX.length()));
        } else {
            request.getRequestDispatcher(WEB_INF_PATH_TO_JSP + execute + EXT_JSP).forward(request, response);
            ValidatorHelper.deleteErrorsFromSession(request);
        }
    }

    private String getActionName(HttpServletRequest request) {
        return request.getParameter(ACTION);
    }

    private void checkErrorCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer errorStatusCode = (Integer) request.getAttribute(ERROR_STATUS_CODE);
        if (errorStatusCode != null) {
            request.getRequestDispatcher(WEB_INF_PATH_TO_JSP  + ERROR + errorStatusCode + EXT_JSP).forward(request, response);
            log.error("Received error code {}, will be forwarded to proper error page.", errorStatusCode);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
}
