package event;

import domain.Kweet;

import java.io.Serializable;

/**
 * Created by jordy on 03/28/2017.
 */
public class PostKweetEvent implements Serializable {

    public String userName;
    public String message;

    public PostKweetEvent() {
    }
}
