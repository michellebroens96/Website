package postservice.dao;

import database.dao.BaseDao;
import postservice.model.Post;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;

@RequestScoped
@Default
public class PostDao extends BaseDao<Post> implements IPostDao {
}
