package domain;

import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jordy on 3/19/2017.
 */
public class UserTest {

    /*
    private User user1;
    private User user2;
    private User user3;


    @Before
    public void setUp() throws Exception {
        user1 = new User("user1", 18, "user1@mail.com", "password");
        user2 = new User("user2", 19, "user2@mail.com", "password");
        user3 = new User("user3", 20, "user3@mail.com", "password");
    }

    @Test
    public void followUser() {
        user1.followUser(user2);

        assertFalse(user1.getFollowing().isEmpty());
        assertFalse(user2.getFollowers().isEmpty());

        List following = new ArrayList<User>(user1.getFollowing());
        List followers = new ArrayList<User>(user2.getFollowers());

        assertEquals(following.get(0), user2);
        assertEquals(followers.get(0), user1);

        //Following a person you are already following
        //should not work
        user1.followUser(user2);

        assertEquals(user1.getFollowing().size(), 1);
        assertEquals(user2.getFollowers().size(), 1);


    }

    @Test
    public void unfollowUser() throws Exception {
        user1.followUser(user2);
        List following = new ArrayList<User>(user1.getFollowing());
        List followers = new ArrayList<User>(user2.getFollowers());

        assertEquals(following.get(0), user2);
        assertEquals(followers.get(0), user1);

        user1.unfollowUser(user2);

        assertTrue(user1.getFollowing().isEmpty());
        assertTrue(user2.getFollowers().isEmpty());

    }
    */
}
