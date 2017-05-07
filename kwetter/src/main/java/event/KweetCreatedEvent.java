package event;

import domain.Kweet;

import java.io.Serializable;

/**
 * Created by jordy on 04/10/2017.
 */
public class KweetCreatedEvent implements Serializable {

    public Kweet kweet;

    public KweetCreatedEvent() {
    }
}
