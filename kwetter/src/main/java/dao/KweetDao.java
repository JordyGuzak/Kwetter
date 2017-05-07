package dao;

import domain.Kweet;
import domain.User;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by Jordy on 15-2-2017.
 */
public interface KweetDao {
    void addKweet(Kweet kweet);
    void removeKweet(Kweet kweet);
    void likeKweet(Kweet kweet, User user);
    Collection<Kweet> getAll();
    Collection<Kweet> getAllKweetsByUser(User user);
    Collection<Kweet> getRecentKweets(User user, int startPosition, int limit);
    Collection<Kweet> getTimeLine(User user, int limit);
    Collection<Kweet> searchKweets(User user, String query);
    Long getKweetCount(User user);

}
