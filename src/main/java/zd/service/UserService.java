package zd.service;

import zd.dao.DaoFactory;
import zd.dao.UserDao;
import zd.exception.DaoException;
import zd.exception.ServiceException;
import zd.exception.UserNotFoundException;
import zd.exception.WrongPasswordException;
import zd.model.user.User;
import zd.util.PasswordHelper;

import java.util.ArrayList;

/**
 * Zhannur Diyas
 * 1/24/2017 | 9:54 AM
 */
public class UserService extends AbstractService {

    private static final String GET_USER_BY_USERNAME = "get.user.by.username";

    public User login(User user) throws ServiceException, UserNotFoundException {
        parameters = new ArrayList<>();
        parameters.add(user.getUsername());
        String testPassword = user.getPassword();
        User foundUser;
        try {
            DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory();
            UserDao userDao = daoFactory.getUserDao();
            foundUser = userDao.getByParameters(user, parameters, GET_USER_BY_USERNAME);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (foundUser == null) {
            throw new UserNotFoundException();
        }
        try {
            if (!PasswordHelper.verifyPassword(testPassword, foundUser.getPassword())) {
                throw new WrongPasswordException();
            }
        } catch (PasswordHelper.PasswordHasherAlgorithmException e) {
            throw new ServiceException(e);
        }
        return foundUser;
    }

    public User register(User user) throws ServiceException {
        return null;
    }
}
