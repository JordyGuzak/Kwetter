package dao;

import domain.Kweet;
import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by Jordy on 15-2-2017.
 */
@Stateless @Default
public class KweetDaoColl implements KweetDao {

    public void addKweet(Kweet kweet) {
        kweet.getOwner().getKweets().add(kweet);
    }

    public void removeKweet(Kweet kweet) {
        kweet.getOwner().getKweets().remove(kweet);
    }

    public void likeKweet(Kweet kweet, User user) {
        kweet.getLikedByUsers().add(user);
    }

    public Collection<Kweet> getAll() {
        return null;
    }

    public Collection<Kweet> getAllKweetsByUser(User user) {
        return user.getKweets();
    }

    public Collection<Kweet> getRecentKweets(User user, int startPosition, int length) {
        return user.getKweets();//TODO
    }

    public Collection<Kweet> getTimeLine(User user, int limit) {
        return user.getKweets(); //TODO
    }

    public Collection<Kweet> searchKweets(User user, String query) {
        return null;
    }

    public Long getKweetCount(User user) {
        return 0L;
    }
}
