package bean;

import dao.JPA;
import domain.Kweet;
import domain.User;
import event.PostKweetEvent;
import org.primefaces.context.RequestContext;
import service.KweetService;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jordy on 03/28/2017.
 */
@Named(value = "kweetBean")
@RequestScoped
public class KweetBean {

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    private String message;


    @Inject
    private Event<PostKweetEvent> postKweetEvent;


    public void postKweet() {

        if (isLoggedIn() && message != null && !message.contentEquals("")) {
            PostKweetEvent payload = new PostKweetEvent();
            payload.userName = getPrincipalName();
            payload.message = message;
            postKweetEvent.fire(payload);
            RequestContext.getCurrentInstance().execute("onPostKweet('" + getPrincipalName() + "', '" + message + "')");
            this.message = null;
        }
    }

    public Collection<Kweet> getRecentKweets(User user) {
        if (user != null) {
            return kweetService.getRecentKweets(user);
        } else {
            return null;
        }
    }

    public Kweet getLatestKweet(User user) {
        ArrayList<Kweet> recent = new ArrayList<Kweet>(getRecentKweets(user));

        if (!recent.isEmpty()) {
            return recent.get(0);
        }
        return null;
    }

    public Collection<Kweet> getTimeLine(User user) {
        if (user != null) {
            return kweetService.getTimeLine(user, 20);
        } else {
            return null;
        }
    }

    public Long getKweetCount(User user) {
        return kweetService.getKweetCount(user);
    }

    public Collection<Kweet> getKweets() {
        return kweetService.getAll();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
    }

    public String getPrincipalName() {
        if (isLoggedIn()) {
            return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        } else {
            return null;
        }
    }
}
