package zd.action;

import zd.dao.DaoFactory;
import zd.exception.ValidatorException;
import zd.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:44 PM
 */
public class RegisterAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ValidatorException {
        DaoFactory daoFactory = DaoFactory.createFactory();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, String> formData = new HashMap<>();
        formData.put("username", username);
        formData.put("password", password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

/*        UserDao userDAO = daoFactory.getUserDao();
        userDAO.insert(user);*/

        return "redirect:register-success";
    }
}
