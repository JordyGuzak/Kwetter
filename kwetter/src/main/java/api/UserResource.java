package api;

import domain.Mention;
import domain.User;
import model.FollowModel;
import model.ResultLinks;
import model.UnfollowModel;
import model.UserModel;
import service.MentionService;
import service.UserService;
import util.UserFilter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jordy on 3/4/2017.
 */
@Path("/user")
@Consumes("application/json")
@Produces("application/json")
public class UserResource {

    @Inject
    UserService service;

    @Inject
    MentionService mentionService;

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/all")
    public Response getUsers() {
        Collection<User> users = UserFilter.filterAll(service.getUsers());
        GenericEntity<Collection<User>> entity = new GenericEntity<Collection<User>>(users){};
        return Response.ok(entity).build();
    }

    @GET
    @Path("{name}")
    public Response getUserByName(@PathParam("name") String name) {
        User user = UserFilter.filter(service.findUserByName(name));
        return Response.ok(user).build();
    }

    @GET
    @Path("{id}/followers")
    public Response getFollowers(@PathParam("id") Long id) {
        Collection<User> followers = UserFilter.filterAll(service.getFollowers(id));
        GenericEntity<Collection<User>> entity = new GenericEntity<Collection<User>>(followers){};
        return Response.ok(entity).build();
    }

    @GET
    @Path("{id}/linksoffollowers")
    public Response getLinksOfFollowers(@PathParam("id") Long id) {
        Collection<User> followers = UserFilter.filterAll(service.getFollowers(id));
        ResultLinks result = new ResultLinks();

        followers.forEach(follower -> {
            follower.setSelf(Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass()).path(getClass(), "getUserByName")
                    .build(follower.getUsername())).rel("follower").type("GET").build());
            result.addLink(follower.getSelf());
        });

        return Response.ok(result).build();
    }

    @GET
    @Path("{id}/following")
    public Response getFollowing(@PathParam("id") Long id) {
        Collection<User> followers = UserFilter.filterAll(service.getFollowing(id));
        GenericEntity<Collection<User>> entity = new GenericEntity<Collection<User>>(followers){};
        return Response.ok(entity).build();
    }

    @POST
    @Path("create")
    public Response createUser(UserModel model) {
        try {
            User user = new User(model.getUsername(), model.getAge(), model.getEmail(), model.getPassword());
            service.addUser(user);
            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Path("remove")
    public Response removeUser(String name) {
        try {
            User user = service.findUserByName(name);
            service.removeUser(user);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Path("follow")
    public Response followUser(FollowModel model) {
        try {
            service.followUser(model.getUser(), model.getUserToFollow());
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @POST
    @Path("unfollow")
    public Response unFollowUser(UnfollowModel model) {
        try {
            service.unFollowUser(model.getUser(), model.getUserToUnFollow());
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e).build();
        }
    }

    @GET
    @Path("{username}/mentions")
    public Response getMentions(@PathParam("username") String username) {
        try {
            Collection<Mention> mentions = mentionService.getMentions(username);
            GenericEntity<Collection<Mention>> entity = new GenericEntity<Collection<Mention>>(mentions) {};
            return Response.ok(entity).build();
        } catch(Exception e) {
            return Response.serverError().entity("ERROR").build();
        }
    }
}
