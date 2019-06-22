package userservice.dao;

import database.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import userservice.model.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;

@RequestScoped
@Default
public class UserDao extends BaseDao<User> implements IUserDao {
    @Override
    public User getUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
