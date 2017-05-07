package service;

import dao.TrendDao;
import domain.Kweet;
import domain.Trend;
import event.KweetCreatedEvent;
import interceptor.Logged;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jordy on 04/10/2017.
 */
@Stateless
public class TrendService {

    @Inject @Default
    private TrendDao trendDao;

    @PermitAll
    @Logged
    public void findTrends(@Observes KweetCreatedEvent event) {

        Kweet kweet = event.kweet;

        String content = kweet.getMessage();
        String pattern = "(?:\\s|\\A|^)[##]+([A-Za-z0-9-_]+)";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(content);
        while (m.find()) {
            String hashtag = m.group(0).trim();
            Trend trend = new Trend(hashtag);
            trendDao.addTrend(trend, kweet);
        }
    }

    @PermitAll
    public Collection<Trend> getTrends() {
        return trendDao.getTrends();
    }

}
