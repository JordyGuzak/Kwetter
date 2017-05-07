package api;

import api.UserResource;
import domain.User;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.PasswordHash;


import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by jordy on 3/20/2017.
 */
public class UserResourceTest extends JerseyTest {

    /*
    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(UserResource.class);
    }

    @Test
    public void getUsers() {
        Response output = target("/user/all").request().get();
        assertEquals(200, output.getStatus());
    }

    @Test
    public void getUserByName() {

    }

    @Test
    public void createUser() {
        User user = new User("test", 21, "test@mail.com", PasswordHash.stringToHash("password"));
        Entity<User> entity = Entity.entity(user, MediaType.APPLICATION_JSON);

        User result = this.root.request().post(entity, User.class);
        assertThat(result, is(user));
    }

    @Test
    public void removeUser() {

    }

    @Test
    public void followUser() {

    }

    @Test
    public void unFollowUser() {

    }

    */

}