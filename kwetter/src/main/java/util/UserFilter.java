package util;

import domain.User;

import java.util.Collection;

/**
 * Created by jordy on 04/09/2017.
 */
public class UserFilter {

    public static User filter(User user) {
        user.setFollowers(null);
        user.setFollowing(null);
        user.setKweets(null);
        user.setGroups(null);
        user.setPassword(null);
        user.setMentions(null);

        return user;
    }

    public static Collection<User> filterAll(Collection<User> users) {
        for (User user : users) {
            filter(user);
        }
        return users;
    }
}
