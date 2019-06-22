package userservice.service;

import database.service.BaseService;
import lombok.NonNull;
import org.mindrot.jbcrypt.BCrypt;
import userservice.dao.IUserDao;
import userservice.model.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;


@RequestScoped
@Default
public class UserService extends BaseService<User, IUserDao> implements IUserService {

    @Override
    public User executeLogin(String username, String password) {
        User user = dao.getUserByUsername(username);

        if (user == null) {
            return null;
        }

        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }

        return null;
    }

    @Override
    public User getByUsername(String username) {
        return dao.getUserByUsername(username);
    }

    @Override
    public User add(@NonNull User entity) {
        entity.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(5)));

        return super.add(entity);
    }
}
