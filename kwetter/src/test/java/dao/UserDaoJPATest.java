package dao;

import domain.User;
import org.junit.*;
import util.DatabaseCleaner;
import util.PasswordHash;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by jordy on 3/14/2017.
 */
public class UserDaoJPATest {

/*
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-pu");
    private EntityManager em;
    private EntityTransaction tx;
    UserDaoJPA userDao;

    public UserDaoJPATest() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJPATest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDao = new UserDaoJPA();
        userDao.setEm(em);
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void addUser() {

        User user = new User("user1", 12, "user1@mail.com", PasswordHash.stringToHash("password"));

        tx.begin();
        userDao.addUser(user);
        tx.commit();

        tx.begin();
        Collection<User> users = userDao.getUsers();
        tx.commit();

        assertEquals(1, users.size());

    }

    @Test
    public void removeUser() {
        User user = new User("user1", 12, "user1@mail.com", PasswordHash.stringToHash("password"));

        tx.begin();
        userDao.addUser(user);
        tx.commit();

        tx.begin();
        userDao.removeUser(user);
        tx.commit();

        tx.begin();
        Collection<User> users = userDao.getUsers();
        tx.commit();

        assertEquals(0, users.size());
    }

    @Test
    public void followUser() {
        User user1 = new User("user1", 12, "user1@mail.com", PasswordHash.stringToHash("password"));
        User user2 = new User("user2", 12, "user2@mail.com", PasswordHash.stringToHash("password"));

        tx.begin();
        userDao.addUser(user1);
        userDao.addUser(user2);
        tx.commit();

        tx.begin();
        userDao.followUser(user1, user2);
        tx.commit();

        tx.begin();
        ArrayList<User> users = new ArrayList<User>(userDao.getUsers());
        tx.commit();

        assertFalse(users.get(0).getFollowing().isEmpty());
        assertFalse(users.get(1).getFollowers().isEmpty());

    }

    @Test
    public void unFollowUser() {
        User user1 = new User("user1", 12, "user1@mail.com", PasswordHash.stringToHash("password"));
        User user2 = new User("user2", 12, "user2@mail.com", PasswordHash.stringToHash("password"));

        tx.begin();
        userDao.addUser(user1);
        userDao.addUser(user2);
        tx.commit();

        tx.begin();
        userDao.followUser(user1, user2);
        tx.commit();

        tx.begin();
        userDao.unFollowUser(user1, user2);
        tx.commit();

        tx.begin();
        ArrayList<User> users = new ArrayList<User>(userDao.getUsers());
        tx.commit();

        assertTrue(users.get(0).getFollowing().isEmpty());
        assertTrue(users.get(1).getFollowers().isEmpty());
    }

    @Test
    public void findUserByName() {
        User user1 = new User("user1", 12, "user1@mail.com", PasswordHash.stringToHash("password"));
        User user2 = new User("user2", 12, "user2@mail.com", PasswordHash.stringToHash("password"));

        tx.begin();
        userDao.addUser(user1);
        userDao.addUser(user2);
        tx.commit();

        tx.begin();
        User u = userDao.findUserByName("user1");
        tx.commit();

        assertNotNull(u);
        assertEquals(user1.getName(), u.getName());
    }

    @Test
    public void getUsers() {

    }
*/
}