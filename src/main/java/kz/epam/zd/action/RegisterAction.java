package kz.epam.zd.action;

import kz.epam.zd.exception.ActionException;
import kz.epam.zd.exception.DaoException;
import kz.epam.zd.dao.DaoFactory;
import kz.epam.zd.dao.UserDao;
import kz.epam.zd.model.user.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import static kz.epam.zd.util.ConstantHolder.USER;

public class RegisterAction implements Action {
    private static final String REGISTER_USER_KEY = "insert.user";
    private static final String REDIRECT_REGISTER_SUCCESS = "redirect:/do/?action=show-register-success";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        List<Object> parameters = new ArrayList<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        parameters.add(username);
        parameters.add(password);
        User user = new User(username, password);
        try {
            DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory();
            UserDao userDAO = daoFactory.getUserDao();
            userDAO.insert(user, parameters, REGISTER_USER_KEY);
        } catch (DaoException e) {
            throw new ActionException(e);
        }
        request.getSession().setAttribute(USER, user);
        return REDIRECT_REGISTER_SUCCESS;
    }
}
