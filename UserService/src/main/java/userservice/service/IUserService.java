package userservice.service;


import database.service.IBaseService;
import userservice.model.User;

public interface IUserService extends IBaseService<User> {
    /**
     * Finds the user for the given login credentials.
     * Uses the underlying hashing algorithm to verify the password.
     * @param username - the username of the user trying to log in.
     * @param password - the provided password.
     * @return the received user or null.
     */
    User executeLogin(String username, String password);

    /**
     * Gets the user based on his/her username.
     * @param username - the username to be searched for.
     * @return the found user or null.
     */
    User getByUsername(String username);
}
