package dao;

import domain.Kweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jordy on 3/14/2017.
 */
@Stateless
@JPA
public class KweetDaoJPA extends DaoFacade<Kweet> implements KweetDao {

    @PersistenceContext(unitName = "kwetter-local-pu")
    private EntityManager em;

    public KweetDaoJPA() {
        super(Kweet.class);
    }

    public void addKweet(Kweet kweet) {
        create(kweet);
    }

    public void removeKweet(Kweet kweet) {
        remove(kweet);
    }

    public void likeKweet(Kweet kweet, User user) {
        Kweet k = em.merge(kweet);
        k.likeKweet(user);
    }

    public Collection<Kweet> getAll() {
        Query query = em.createQuery("SELECT k FROM Kweet k");
        return new ArrayList<Kweet>(query.getResultList());
    }

    public Collection<Kweet> getAllKweetsByUser(User user) {
        Query query = em.createQuery("SELECT k FROM Kweet k WHERE k.owner = :user")
                .setParameter("user", user);
        return new ArrayList<Kweet>(query.getResultList());
    }

    public Collection<Kweet> getRecentKweets( User user, int startPosition, int limit) {

        if (startPosition < 0 || limit < 0) {
            throw new IllegalArgumentException();
        } else {
            return em.createNamedQuery("kweet.getRecentKweets", Kweet.class)
                    .setParameter("user", user)
                    .setFirstResult(startPosition)
                    .setMaxResults(limit)
                    .getResultList();
        }
    }

    public Collection<Kweet> getTimeLine(User user, int limit) {
        return em.createNamedQuery("kweet.getTimeLine", Kweet.class)
                .setParameter("user", user)
                .setMaxResults(limit)
                .getResultList();
    }

    public Collection<Kweet> searchKweets(User user, String query) {
        return em.createNamedQuery("kweet.searchKweets", Kweet.class)
                .setParameter("user", user)
                .setParameter("query", "%" + query + "%")
                .getResultList();
    }

    public Long getKweetCount(User user) {
        return  em.createNamedQuery("kweet.getKweetCount", Long.class)
                .setParameter("user", user)
                .getResultList().get(0);
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
