package postservice.dao;

import database.dao.LocalBaseDao;
import postservice.model.Post;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class PostDaoMock extends LocalBaseDao<Post> implements IPostDao   {
}
