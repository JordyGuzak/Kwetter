package event;

import dao.JPA;
import service.KweetService;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by jordy on 03/28/2017.
 */
@RequestScoped
public class PostKweetHandler implements Serializable {

    /*@Inject
    private KweetService kweetService;

    @Inject
    UserService userService;

    public void handleKweet(@Observes PostKweetEvent event) {
        service.addKweet(event.kweet);
    }
    */

}
