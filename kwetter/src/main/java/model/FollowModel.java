package model;

import domain.User;

/**
 * Created by jordy on 3/19/2017.
 */
public class FollowModel {

    private User user;
    private User userToFollow;

    public FollowModel() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserToFollow() {
        return userToFollow;
    }

    public void setUserToFollow(User userToFollow) {
        this.userToFollow = userToFollow;
    }
}
