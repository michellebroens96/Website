package postservice.rest;

import database.helper.ModelMapperHelper;
import database.resource.BaseResource;
import lombok.NonNull;
import postservice.dto.PostDto;
import postservice.model.Post;
import postservice.service.IPostService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/posts")
public class PostResource extends BaseResource {

    @Inject
    private IPostService postService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPosts() {
        return createListResponse(postService.getAll(), PostDto.class);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPost(@PathParam("id") Long id) {
        return createSingleResponse(postService.get(id), PostDto.class);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPost(@NonNull PostDto post) throws Exception {
        postService.add(ModelMapperHelper.getModdelMapper().map(post, Post.class));
        return Response.accepted().build();
    }
}
