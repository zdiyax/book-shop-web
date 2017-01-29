package zd.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.action.Action;
import zd.action.ActionFactory;
import zd.exception.ActionException;
import zd.exception.ActionFactoryException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static zd.util.ConstantHolder.*;

@WebServlet(name = "FrontControllerServlet", urlPatterns="/do/*")
public class FrontControllerServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(FrontControllerServlet.class);
    private ActionFactory actionFactory;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkErrorCode(req, resp);
        String actionName = getActionName(req);
        try {

            Action action = actionFactory.getAction(actionName);
            String execute = action.execute(req, resp);
            proceedTo(execute, req, resp);
        } catch (ActionException | ActionFactoryException e) {
            log.error("Error in FrontControllerServlet occurred", e);
        }
    }

    private void proceedTo(String execute, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (execute.startsWith(REDIRECT_PREFIX)) {
            resp.sendRedirect(execute.substring(REDIRECT_PREFIX.length()));
        } else {
            req.getRequestDispatcher(WEB_INF_PATH_TO_JSP + execute + EXT_JSP).forward(req, resp);
        }
    }

    private String getActionName(HttpServletRequest req) {
        return req.getParameter(ACTION);
    }

    @Override
    public void init() throws ServletException {
            actionFactory = new ActionFactory();
            try {
                actionFactory.loadActions();
            } catch (ActionFactoryException e) {
                log.error("Action Factory error in controller occurred", e);
            }
    }

    private void checkErrorCode(HttpServletRequest req, HttpServletResponse resp) {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
}
