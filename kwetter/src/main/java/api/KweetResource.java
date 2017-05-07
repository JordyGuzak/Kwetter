package api;

import domain.Kweet;
import domain.Trend;
import domain.User;
import event.PostKweetEvent;
import model.LikeKweetModel;
import model.PostKweet;
import model.Result;
import service.KweetService;
import service.TrendService;
import service.UserService;
import util.KweetFilter;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Collection;

/**
 * Created by jordy on 3/19/2017.
 */
@Path("/kweet")
@Consumes(value = "application/json")
@Produces(value = "application/json")
public class KweetResource {

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    @Inject
    private TrendService trendService;

    @Inject
    private Event<PostKweetEvent> postKweetEvent;

    @POST
    @Path("create")
    public Response addKweet(PostKweet post) {
        if (post.getUsername() != null && post.getMessage() != null) {
            PostKweetEvent payload = new PostKweetEvent();
            payload.userName = post.getUsername();
            payload.message = post.getMessage();
            postKweetEvent.fire(payload);
            return Response.ok(new Result(true, "Success.")).build();
        }
        return Response.serverError().entity(new Result(false, "Failed to post kweet.")).build();
    }

    @POST
    @Path("remove")
    public Response removeKweet(Kweet kweet) {
        kweetService.removeKweet(kweet);
        return Response.ok().build();
    }

    @POST
    @Path("like")
    public Response likeKweet(LikeKweetModel model) {
        kweetService.likeKweet(model.getKweet(), model.getUser());
        return Response.ok().build();
    }

    @GET
    @Path("/all/{username}")
    public Response getAllKweetsOfUser(@PathParam("username") String username) {
        User user = userService.findUserByName(username);
        Collection<Kweet> kweets = KweetFilter.filterAll(kweetService.getAllKweetsOfUser(user));
        GenericEntity<Collection<Kweet>> entity = new GenericEntity<Collection<Kweet>>(kweets){};

        return Response.ok(entity).build();
    }

    @GET
    @Path("/recent/{username}")
    public Response getRecentKweets(@PathParam("username") String username) {
        User user = userService.findUserByName(username);
        Collection<Kweet> kweets = KweetFilter.filterAll(kweetService.getRecentKweets(user));
        GenericEntity<Collection<Kweet>> entity = new GenericEntity<Collection<Kweet>>(kweets){};

        return Response.ok(entity).build();
    }

    @GET
    @Path("/timeline/{username}")
    public Response getTimeLine(@PathParam("username") String username) {
        User user = userService.findUserByName(username);
        Collection<Kweet> kweets = KweetFilter.filterAll(kweetService.getTimeLine(user, 15));
        GenericEntity<Collection<Kweet>> entity = new GenericEntity<Collection<Kweet>>(kweets){};

        return Response.ok(entity).build();
    }

    @GET
    @Path("/search/{username}/{query}")
    public Response searchKweets(@PathParam("username") String username , @PathParam("query") String query) {
        Collection<Kweet> kweets = KweetFilter.filterAll(kweetService.searchKweets(username, query));
        GenericEntity<Collection<Kweet>> entity = new GenericEntity<Collection<Kweet>>(kweets){};
        return Response.ok(entity).build();
    }

    @GET
    @Path("/trends")
    public Response getTrends() {
        Collection<Trend> trends = trendService.getTrends();
        GenericEntity<Collection<Trend>> entity = new GenericEntity<Collection<Trend>>(trends){};
        return Response.ok(entity).build();
    }

    @GET
    @Path("/count/{username}")
    public Response getKweetCount(@PathParam("username") String username) {
        User user = userService.findUserByName(username);
        Long kweetCount = kweetService.getKweetCount(user);
        return Response.ok(new Result(true, "success", kweetCount)).build();
    }
}
