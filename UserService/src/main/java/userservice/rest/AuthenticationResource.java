package userservice.rest;

import database.helper.ModelMapperHelper;
import database.resource.BaseResource;
import userservice.dto.CreateUserDto;
import userservice.dto.LoginUserDto;
import userservice.dto.OutgoingTokenDto;
import userservice.dto.ReceivingTokenDto;
import userservice.helper.JwtTokenHelper;
import userservice.model.User;
import userservice.service.IUserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationResource extends BaseResource {

    @Inject
    private IUserService userService;

    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginUserDto userDto) {
        User user = userService.executeLogin(userDto.getUsername(), userDto.getPassword());
        if(user == null || !user.getScope().equals(userDto.getScope())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        JwtTokenHelper jwtTokenHelper = new JwtTokenHelper();
        String token = jwtTokenHelper.generateToken(user.getId().toString());

        OutgoingTokenDto tokenDto = new OutgoingTokenDto();
        tokenDto.setJwtToken(token);
        tokenDto.setFullName(user.getFullName());

        return Response.ok(tokenDto).build();
    }

    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreateUserDto userDto) {
        User user = userService.getByUsername(userDto.getUsername());
        if(user != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        user = ModelMapperHelper.getModdelMapper().map(userDto, User.class);

        try {
            userService.add(user);
        }
        catch(Exception e) {
            return Response.serverError().build();
        }

        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userInfoByBody(ReceivingTokenDto token) {
        User user = executeAuthentication(token);
        if(user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.noContent().build();
    }

    private User executeAuthentication(ReceivingTokenDto dto) {
        Long subjectId;
        try {
            JwtTokenHelper jwtTokenHelper = new JwtTokenHelper();
            String subject = jwtTokenHelper.validateToken(dto.getToken());
            subjectId = Long.parseLong(subject);
        }
        catch(Exception exception) {
            return null;
        }

        User user = userService.get(subjectId);

        if(user == null) {
            return null;
        }


        if(!user.getScope().equals(dto.getScope())) {
            return null;
        }

        return user;
    }
}
