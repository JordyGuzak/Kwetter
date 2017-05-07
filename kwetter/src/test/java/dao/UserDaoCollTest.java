package dao;

import dao.UserDao;
import dao.UserDaoColl;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jordy on 3/10/2017.
 */
public class UserDaoCollTest {

    /*
    UserDao userDao;
    User user1;
    User user2;
    User user3;
    User user4;
    User user5;

    @Before
    public void setUp() throws Exception {
        userDao = new UserDaoColl();
        user1 = new User("user1", 16, "user1@mail.com", "password");
        user2 = new User("user2", 21, "user2@mail.com", "password");
        user3 = new User("user3", 19, "user3@mail.com", "password");
        user4 = new User("user4", 26, "user4@mail.com", "password");
        user5 = new User("user5", 23, "user5@mail.com", "password");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addUser() throws Exception {
        //The initial number of users should be 0.
        assertEquals(0, userDao.getUsers().size());
        //Adding all (5) users.
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);
        userDao.addUser(user4);
        userDao.addUser(user5);
        //Number of users should be 5.
        assertEquals(5, userDao.getUsers().size());
    }

    @Test
    public void removeUser() throws Exception {
        //The initial number of users should be 0.
        assertEquals(0, userDao.getUsers().size());
        //Adding all (5) users.
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);
        userDao.addUser(user4);
        userDao.addUser(user5);
        //removing 1 user;
        userDao.removeUser(user5);
        //Number of users should be 4.
        assertEquals(4, userDao.getUsers().size());
    }

    @Test
    public void followUser() throws Exception {
        //The initial number of users should be 0.
        assertEquals(0, userDao.getUsers().size());
        //Adding three users.
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);
        //user1 follows user2
        userDao.followUser(user2, user1);
        //user2's list of followers should be equal to 1
        assertEquals(1, user2.getFollowers().size());
        //user1's list of following should be equal to 1
        assertEquals(1, user1.getFollowing().size());
    }

    @Test
    public void unFollowUser() throws Exception {
        //The initial number of users should be 0.
        assertEquals(0, userDao.getUsers().size());
        //Adding three users.
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);
        //user1 follows user2
        userDao.followUser(user2, user1);
        //user2's list of followers should be equal to 1
        assertEquals(1, user2.getFollowers().size());
        //user1's list of following should be equal to 1
        assertEquals(1, user1.getFollowing().size());
        //unfollow
        userDao.unFollowUser(user2, user1);
        //user2's list of followers should be equal to 0
        assertEquals(0, user2.getFollowers().size());
        //user1's list of following should be equal to 0
        assertEquals(0, user1.getFollowing().size());
    }

    @Test
    public void getUsers() throws Exception {
        //The initial number of users should be 0.
        assertEquals(0, userDao.getUsers().size());
        //Adding three users.
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);
        //The number of users should be equal to 3
        assertEquals(3, userDao.getUsers().size());
    }

    @Test
    public void findUserByName() throws Exception {
        //The initial number of users should be 0.
        assertEquals(0, userDao.getUsers().size());
        //Adding three users.
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);
        userDao.addUser(new User("Jordy", 10, "jordy@mail.com", "password"));

        User user = userDao.findUserByName("Jordy");

        assertNotNull(user);
        assertEquals("Jordy", user.getName());

        User failUser = userDao.findUserByName("asdf");

        assertNull(failUser);

        try {
            User failUser2 = userDao.findUserByName(null);
            fail("Passing null value as parameter is not allowed.");
        } catch (Exception e) {
            System.out.println("Passed");
        }
    }
    */

}