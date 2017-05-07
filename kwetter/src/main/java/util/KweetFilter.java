package util;

import domain.Kweet;

import java.util.Collection;

/**
 * Created by jordy on 04/09/2017.
 */
public class KweetFilter {

    public static Kweet filter(Kweet kweet) {
        kweet.setUsername(kweet.getOwner().getUsername());
        kweet.setOwner(null);
        kweet.setLikedByUsers(null);

        return kweet;
    }

    public static Collection<Kweet> filterAll(Collection<Kweet> kweets) {
        for (Kweet kweet : kweets) {
            filter(kweet);
        }
        return kweets;
    }
}
