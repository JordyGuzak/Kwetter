package model;

import domain.Kweet;
import domain.User;

/**
 * Created by jordy on 3/19/2017.
 */
public class LikeKweetModel {

    private Kweet kweet;
    private User user;

    public LikeKweetModel() {
    }

    public LikeKweetModel(Kweet kweet, User user) {
        this.kweet = kweet;
        this.user = user;
    }

    public Kweet getKweet() {
        return kweet;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
