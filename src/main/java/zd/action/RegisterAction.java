package zd.action;

import zd.dao.DaoFactory;
import zd.dao.UserDao;
import zd.exception.ActionException;
import zd.exception.DaoException;
import zd.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        try {
            DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory();
            UserDao userDAO = daoFactory.getUserDao();
            userDAO.insert(user);
        } catch (DaoException e) {
            throw new ActionException(e);
        }

        return "redirect:register-success";
    }
}
