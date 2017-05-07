package util;

import domain.Kweet;
import domain.Mention;
import domain.Trend;
import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jordy on 04/10/2017.
 */
@Stateless
public class KweetParser {

    @Inject
    private UserService userService;

    /**
     * Finds all Trends in a given Kweet.
     *
     * @param kweet The Tweet which will be parsed.
     * @return A list of Trends.
     */
    public ArrayList<Trend> findHashtags(Kweet kweet) {
        ArrayList<Trend> trends = new ArrayList<Trend>();

        String content = kweet.getMessage();
        String pattern = "(?:\\s|\\A|^)[##]+([A-Za-z0-9-_]+)";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(content);
        while (m.find()) {
            String hashtag = m.group(0).trim();
            Trend tempTrend = new Trend(hashtag);

            if (trends.contains(tempTrend)) {
                int trendsIndex = trends.indexOf(tempTrend);
                trends.get(trendsIndex).setHashCount(tempTrend.getHashCount() + 1);
            } else {
                Trend trend = new Trend(hashtag);
                trend.setFirstUsed(kweet.getPostDate());
                trends.add(trend);
            }
        }

        return trends;
    }

    /**
     * Finds all Mentions in a given Kweet.
     *
     * @param kweet The Tweet which will be parsed.
     * @return A list of Mentions.
     */
    public ArrayList<Mention> findMentions(Kweet kweet) {
        ArrayList<Mention> mentions = new ArrayList<Mention>();

        String content = kweet.getMessage();
        String pattern = "@([a-zA-Z0-9]+)";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(content);
        while (m.find()) {
            User foundUser = userService.findUserByName(m.group(0).replace("@", ""));
            if (foundUser != null) {
                mentions.add(new Mention(kweet, foundUser));
            }
        }

        return mentions;
    }
}
