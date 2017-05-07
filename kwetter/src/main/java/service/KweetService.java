package service;

import dao.JPA;
import dao.KweetDao;
import dao.MentionDaoJPA;
import domain.Kweet;
import domain.User;
import event.KweetCreatedEvent;
import event.PostKweetEvent;
import util.KweetParser;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by jordy on 3/14/2017.
 */
@Stateless
public class KweetService {

    @Inject
    @JPA
    private KweetDao kweetDao;

    @Inject
    private KweetParser kweetParser;

    @Inject
    private UserService userService;

    @Inject
    private Event<KweetCreatedEvent> kweetCreatedEvent;


    @PermitAll
    public Collection<Kweet> searchKweets(String username, String query) {
        if (username != null && query != null) {
            User user = userService.findUserByName(username);
            if (user != null) {
                return kweetDao.searchKweets(user, query);
            }
        }
        return null;
    }

    @PermitAll
    public void addKweet(@Observes PostKweetEvent event) {
        User owner = userService.findUserByName(event.userName);

        if (owner != null) {
            Kweet kweet = new Kweet(owner, event.message);
            addKweet(kweet);
        }
    }

    /**
     * Adds a new kweet.
     * @param kweet
     */
    //@RolesAllowed({"UserRole"})
    @PermitAll
    public void addKweet(Kweet kweet) {
        kweetDao.addKweet(kweet);

        //Notify KweetCreatedEvent listeners
        KweetCreatedEvent payload = new KweetCreatedEvent();
        payload.kweet = kweet;
        kweetCreatedEvent.fire(payload);
    }

    /**
     * Removes a kweet.
     * @param kweet
     */
    @RolesAllowed({"AdminRole"})
    public void removeKweet(Kweet kweet){
        kweetDao.removeKweet(kweet);
    }

    /**
     * User likes a kweet made by another user.
     * @param kweet
     * @param user
     */
    //@RolesAllowed({"UserRole"})
    @PermitAll
    public void likeKweet(Kweet kweet, User user){
        kweetDao.likeKweet(kweet, user);
    }

    /**
     * Get all kweets of given user.
     * @param user
     * @return Collection of kweets
     */
    @PermitAll
    public Collection<Kweet> getAllKweetsOfUser(User user){
        return kweetDao.getAllKweetsByUser(user);
    }

    /**
     * Get recent (last 10) kweets of given user.
     * @param user
     * @return Collection of kweets
     */
    @PermitAll
    public Collection<Kweet> getRecentKweets(User user) {
        return kweetDao.getRecentKweets(user, 0, 10);
    }

    /**
     * Get all kweets of user and the user he/she follows.
     * @param user
     * @return Collection of kweets
     */
    @PermitAll
    public Collection<Kweet> getTimeLine(User user, int limit) {
        return kweetDao.getTimeLine(user, limit);
    }

    @PermitAll
    public Long getKweetCount(User user) {
        if (user != null) {
            return kweetDao.getKweetCount(user);
        } else {
            return null;
        }
    }

    @PermitAll
    public Collection<Kweet> getAll() {return kweetDao.getAll(); }


}
