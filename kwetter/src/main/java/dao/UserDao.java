package dao;

import domain.Kweet;
import domain.User;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by Jordy on 15-2-2017.
 */
public interface UserDao {
    User logIn(String username, String password);
    void update(User user);
    void addUser(User user);
    void removeUser(User user);
    void followUser(User user,User follower);
    void unFollowUser(User user,User follower);
    User findUserByName(String name);
    User findUserById(Long id);
    Collection<User> getUsers();
    Collection<User> getFollowers(Long userId);
    Collection<User> getFollowing(Long userId);
}
