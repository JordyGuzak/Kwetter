package dao;

import domain.Kweet;
import domain.Trend;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jordy on 04/10/2017.
 */
@Stateless @Default
public class TrendDaoColl implements TrendDao {

    CopyOnWriteArrayList<Trend> trends = new CopyOnWriteArrayList<Trend>();


    public void addTrend(Trend trend, Kweet kweet) {
        if (trends.contains(trend)) {
            int index = trends.indexOf(trend);
            Trend t = trends.get(index);
            t.setHashCount(t.getHashCount() + 1);
            t.getKweets().add(kweet);
            t.setLastUsed(kweet.getPostDate());
        } else {
            trend.setHashCount(1);
            trend.getKweets().add(kweet);
            trend.setFirstUsed(kweet.getPostDate());
            trend.setLastUsed(kweet.getPostDate());
            trends.add(trend);
        }
    }

    public void removeTrend(Trend trend) {
        if (this.trends.contains(trend)) {
            this.trends.remove(trend);
        }
    }

    public Collection<Trend> getTrends() {
        return this.trends;
    }

    public Collection<Trend> getTopTen() {
        return this.trends; //TODO
    }

}
