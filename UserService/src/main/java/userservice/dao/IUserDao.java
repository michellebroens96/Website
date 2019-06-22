package userservice.dao;

import database.dao.IBaseDao;
import userservice.model.User;

public interface IUserDao extends IBaseDao<User> {
    /**
     * Gets a user by the given username.
     * @param username - the username form the user wanting to be gotten.
     * @return the user found for the username or null.
     */
    User getUserByUsername(String username);
}
