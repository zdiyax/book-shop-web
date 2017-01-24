package zd.action;

import zd.dao.DaoFactory;
import zd.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RegisterAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, String> formData = new HashMap<>();
        formData.put("username", username);
        formData.put("password", password);

        User user = new User(username, password);


/*        UserDao userDAO = daoFactory.getUserDao();
        userDAO.insert(user);*/

        return "redirect:register-success";
    }
}
