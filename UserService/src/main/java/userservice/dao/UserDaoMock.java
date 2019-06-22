package userservice.dao;

import database.dao.LocalBaseDao;
import org.mindrot.jbcrypt.BCrypt;
import userservice.model.Scope;
import userservice.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;

import java.util.Optional;

@ApplicationScoped
@Alternative
public class UserDaoMock extends LocalBaseDao<User> implements IUserDao{
    @PostConstruct
    public void init() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test.TBC");
        String password = "Welcome1!";
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(5)));
        user.setFirstName("Test");
        user.setLastName("TBC");
        user.setScope(Scope.USER);
        entities.put(1L, user);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = entities.values().stream().filter(user -> user.getUsername().equals(username)).findFirst();
        if (optionalUser.isEmpty()) {
            return null;
        }

        return optionalUser.get();
    }
}
