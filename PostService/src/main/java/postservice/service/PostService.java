package postservice.service;

import database.service.BaseService;
import postservice.dao.IPostDao;
import postservice.model.Post;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@RequestScoped
@Default
public class PostService extends BaseService<Post, IPostDao> implements IPostService {

    @Inject
    IPostDao postDao;

}
