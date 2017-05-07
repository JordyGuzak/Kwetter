package dao;

import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jordy on 2/22/2017.
 */
@Stateless
@Default
public class UserDaoColl implements UserDao {

    CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<User>();

    public User logIn(String username, String password) {
        return null;
    }

    public void update(User user) {

    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void followUser(User user, User userToFollow) {
        user.addFollowing(userToFollow);
        userToFollow.addFollower(user);
    }

    public void unFollowUser(User user, User userToUnFollow) {
        user.removeFollowing(userToUnFollow);
        userToUnFollow.removeFollower(user);
    }

    public Collection<User> getUsers() {
        return this.users;
    }

    public Collection<User> getFollowers(Long userId) {
        return null;
    }

    public Collection<User> getFollowing(Long userId) {
        return null;
    }

    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getUsername().contentEquals(name)) {
                return user;
            }
        }
        return null;
    }

    public User findUserById(Long id) {
        return null;
    }
}
