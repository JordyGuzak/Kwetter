package model;

import domain.User;

/**
 * Created by jordy on 3/19/2017.
 */
public class UnfollowModel {

    private User user;
    private User userToUnFollow;

    public UnfollowModel() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserToUnFollow() {
        return userToUnFollow;
    }

    public void setUserToUnFollow(User userToUnFollow) {
        this.userToUnFollow = userToUnFollow;
    }
}
