package service;

import dao.UserDaoJPA;
import domain.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import service.UserService;
import util.PasswordHash;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by jordy on 3/11/2017.
 */
//@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    /*
    UserService service;

    @Mock
    UserDaoJPA userDao;

    User testUser = new User("test", 23, "test@mail.com", "password");

    @Before
    public void setUp() throws Exception {
        service = new UserService();
        service.setUserDao(userDao);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void addUserTest() throws Exception {
        User user = new User("test", 23, "test@mail.com", PasswordHash.stringToHash("password"));
        service.addUser(user);
        verify(userDao, Mockito.times(1)).addUser(user);
    }

    @Test
    public void removeUserTest() throws Exception {
        User user = new User("test", 23, "test@mail.com", PasswordHash.stringToHash("password"));
        service.removeUser(user);
        verify(userDao, Mockito.times(1)).removeUser(user);
    }

    @Test
    public void getUserByName() {
        User user = new User("test", 23, "test@mail.com", PasswordHash.stringToHash("password"));

        when(service.findUserByName("test")).thenReturn(user);
        User found = service.findUserByName(user.getName());
        assertThat(found, is(user));
    }

    */
}
