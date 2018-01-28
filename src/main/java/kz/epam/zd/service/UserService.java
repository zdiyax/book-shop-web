package kz.epam.zd.service;

import kz.epam.zd.dao.DaoFactory;
import kz.epam.zd.dao.UserDao;
import kz.epam.zd.exception.*;
import kz.epam.zd.model.user.User;
import kz.epam.zd.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService extends AbstractService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private static final String USER_LOGIN_KEY = "get.user.by.username";
    private static final String USER_REGISTER_KEY = "insert.user";
    private static final String UPDATE_USER_LOCALE_KEY = "update.user.locale";

    public User login(User user) throws ServiceException {
        parameters.add(user.getUsername());
        String testPassword = user.getPassword();
        User foundUser;
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            UserDao userDao = daoFactory.getUserDao();
            foundUser = userDao.getByParameters(user, parameters, USER_LOGIN_KEY);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (foundUser == null) {
            log.debug("User not found | Username = {}", user.getUsername());
            throw new UserNotFoundException();
        }
        try {
            if (!PasswordHelper.verifyPassword(testPassword, foundUser.getPassword())) {
                log.debug("Wrong password entered | Username = {}", user.getUsername());
                throw new WrongPasswordException();
            }
        } catch (PasswordHelper.PasswordHashAlgorithmException e) {
            throw new ServiceException(e);
        }
        return foundUser;
    }

    public User register(User user) throws ServiceException {
        String hashPassword;
        try {
            //create hashing password from entered password
            hashPassword = PasswordHelper.hash(user.getPassword());
            user.setPassword(hashPassword);
        } catch (PasswordHelper.PasswordHashAlgorithmException e) {
            throw new ServiceException(e);
        }
        parameters.add(user.getUserRole().toString());
        parameters.add(user.getUsername());
        parameters.add(user.getPassword());
        parameters.add(user.getLocale().getLocaleName());
        User registeredUser;
        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            UserDao userDao = daoFactory.getUserDao();
            registeredUser = userDao.insert(user, parameters, USER_REGISTER_KEY);
        } catch (NonUniqueFieldException e) {
            log.debug("User already exists | Username = {}", user.getUsername());
            throw new UserExistsException(e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return registeredUser;
    }

    public void updateUserLocale(User user) throws ServiceException {
        parameters.add(user.getLocale().getLocaleName());
        parameters.add(user.getUsername());

        try (DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory()) {
            UserDao userDao = daoFactory.getUserDao();
            userDao.update(user, parameters, UPDATE_USER_LOCALE_KEY);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
