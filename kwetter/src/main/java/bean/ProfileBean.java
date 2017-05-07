package bean;

import domain.Kweet;
import domain.User;
import org.omnifaces.cdi.Param;
import javax.faces.view.ViewScoped;

import service.KweetService;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by jordy on 03/29/2017.
 */
@Named(value = "profileBean")
@ViewScoped
public class ProfileBean implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    @Inject
    private AuthBean authBean;


    @Inject
    @Param(name = "user")
    private String username;

    private User user;

    @PostConstruct
    public void onLoad() {
        loadUser();
    }

    public Collection<Kweet> getRecentKweets() {
        if (user != null) {
            return kweetService.getRecentKweets(user);
        } else {
            return null;
        }
    }

    public Collection<Kweet> getTimeLine() {
        if (user != null) {
            return kweetService.getTimeLine(user, 10);
        } else {
            return null;
        }
    }


    public Collection<User> getFollowers() {
        System.out.println("Executed getFollowers();");
        if (user != null) {
            return userService.getFollowers(user.getId());
        } else {
            return null;
        }
    }

    public Collection<User> getFollowing() {
        if (user != null) {
            return userService.getFollowing(user.getId());
        } else {
            return null;
        }
    }

    public Long getKweetCount() {
        return kweetService.getKweetCount(user);
    }

    public void followUser() {
        if (isCanFollow()) {
            User currentUser = authBean.getCurrentUser();
            userService.followUser(currentUser, user);
        }
    }

    public void unFollowUser() {
        User currentUser = authBean.getCurrentUser();
        userService.unFollowUser(currentUser, user);
    }

    public boolean isCanFollow() {
        if (user != null && authBean.isLoggedIn() && !user.getUsername().contentEquals(authBean.getPrincipalName()) && !isAlreadyFollowing()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMyProfile() {
        return user.getUsername().contentEquals(authBean.getPrincipalName());
    }

    public boolean isAlreadyFollowing() {
        if (authBean.isLoggedIn()) {
            String username = authBean.getPrincipalName();

            Collection<User> followers = getFollowers();

            for (User u : followers) {
                if (u.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void loadUser() {
        this.user = userService.findUserByName(username);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
