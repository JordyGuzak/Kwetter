package service;

import dao.JPA;
import dao.UserDao;
import domain.Kweet;
import domain.User;
import interceptor.Logged;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by jordy on 2/22/2017.
 */
@Stateless
public class UserService implements Serializable {

    @Inject
    @JPA
    private UserDao userDao;

    public UserService(){}


    @PermitAll
    public void update(User user) {
        userDao.update(user);
    }

    @PermitAll
    public User logIn(String username, String password) {
        if (username != null && password != null) {
            return userDao.logIn(username, password);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Adds a new user.
     * @param user to be added
     */
    //@RolesAllowed({"AdminRole"})
    @Logged
    @PermitAll
    public void addUser(User user) {
        userDao.addUser(user);
    }

    /**
     * Remove user.
     * @param user to be removed
     */
    @RolesAllowed({"AdminRole"})
    public void removeUser(User user) {
        userDao.removeUser(user);
    }

    /**
     * Find user by name.
     * @param name
     * @return user object with given name
     */
    @PermitAll
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    /**
     * Find user by id.
     * @param id
     * @return user object with given id
     */
    @PermitAll
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    /**
     * Adds userToFollow to user's list of following and adds
     * user to userToFollow's list of followers.
     * @param user
     * @param userToFollow
     */
    //@RolesAllowed({"UserRole"})
    @PermitAll
    public void followUser(User user, User userToFollow) {
        userDao.followUser(user, userToFollow);
    }

    /**
     * Removes userToUnFollow from user's list of following and removes
     * user from userToUnFollow's list of followers.
     * @param user
     * @param userToUnFollow
     */
    //@RolesAllowed({"UserRole"})
    @PermitAll
    public void unFollowUser(User user, User userToUnFollow) {
        userDao.unFollowUser(user, userToUnFollow);
    }

    /**
     * Gets all registered users.
     * @return Collection of all users.
     */
    @PermitAll
    public Collection<User> getUsers() {
        return userDao.getUsers();
    }

    /**
     * Gets all following users of user with id.
     * @return Collection of all following.
     */
    //@RolesAllowed({"UserRole"})
    @PermitAll
    public Collection<User> getFollowing(Long userId) {
        return userDao.getFollowing(userId);
    }

    /**
     * Gets all followers of user with id.
     * @return Collection of all followers.
     */
    //@RolesAllowed({"UserRole"})
    @PermitAll
    public Collection<User> getFollowers(Long userId) {
        return userDao.getFollowers(userId);
    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
