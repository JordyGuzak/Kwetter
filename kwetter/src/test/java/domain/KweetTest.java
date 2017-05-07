package domain;

import domain.Kweet;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by jordy on 3/19/2017.
 */
public class KweetTest {


    /*
    private User user1;
    private User user2;
    private User user3;

    Kweet kweet1;
    Kweet kweet2;
    Kweet kweet3;


    @Before
    public void setUp() throws Exception {
        user1 = new User("user1", 18, "user1@mail.com", "password");
        user2 = new User("user2", 19, "user2@mail.com", "password");
        user3 = new User("user3", 20, "user3@mail.com", "password");
    }

    @Test
    public void creationTest() {
        kweet1 = new Kweet(user1, "Hello World!");
        assertNotNull(kweet1);
    }

    @Test
    public void failCreationTest() {

        try {
            kweet1 = new Kweet(null, null);
            fail("null values not allowed");
        } catch (IllegalArgumentException e) {
            System.out.println("failCreationTest test1: success");
        }

        try {
            kweet1 = new Kweet(user1, null);
            fail("null values not allowed");
        } catch (IllegalArgumentException e) {
            System.out.println("failCreationTest test2: success");
        }

        try {
            kweet1 = new Kweet(user1, "");
            fail("empty message not allowed");
        } catch (IllegalArgumentException e) {
            System.out.println("failCreationTest test3: success");
        }
    }

    @Test
    public void likeKweet() throws Exception {
        kweet1 = new Kweet(user1, "Hello World!");

        kweet1.likeKweet(user2);

        assertFalse(kweet1.getLikedByUsers().isEmpty());

        kweet1.likeKweet(user1);

        for (User u : kweet1.getLikedByUsers()) {
            assertNotEquals(u, kweet1.getOwner());
        }

    }
    */

}