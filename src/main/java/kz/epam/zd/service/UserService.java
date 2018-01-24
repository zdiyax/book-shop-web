package kz.epam.zd.service;

import kz.epam.zd.exception.DaoException;
import kz.epam.zd.exception.ServiceException;
import kz.epam.zd.exception.UserNotFoundException;
import kz.epam.zd.exception.WrongPasswordException;
import kz.epam.zd.dao.DaoFactory;
import kz.epam.zd.dao.UserDao;
import kz.epam.zd.model.user.User;
import kz.epam.zd.util.PasswordHelper;

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
