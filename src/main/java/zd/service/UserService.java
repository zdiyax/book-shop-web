package zd.service;

import zd.dao.DaoFactory;
import zd.dao.UserDao;
import zd.exception.DaoException;
import zd.exception.ServiceException;
import zd.exception.UserNotFoundException;
import zd.model.user.User;

/**
 * Zhannur Diyas
 * 1/24/2017 | 9:54 AM
 */
public class UserService extends AbstractService {

    public User login(User user) throws ServiceException, UserNotFoundException {
        final String testPassword = user.getPassword();
        User foundUser;
        try {
            DaoFactory daoFactory = DaoFactory.createJdbcDaoFactory();
            UserDao userDao = daoFactory.getUserDao();
            foundUser = userDao.getById(user.getId());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (foundUser == null) throw new UserNotFoundException("mesaage");
        final String correctHash = foundUser.getPassword();

        return foundUser;
    }
}
