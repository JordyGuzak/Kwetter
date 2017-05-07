package dao;

import domain.Kweet;
import domain.Trend;

import java.util.Collection;

/**
 * Created by jordy on 04/10/2017.
 */
public interface TrendDao {
    void addTrend(Trend trend, Kweet kweet);
    void removeTrend(Trend trend);
    Collection<Trend> getTrends();
    Collection<Trend> getTopTen();
}
