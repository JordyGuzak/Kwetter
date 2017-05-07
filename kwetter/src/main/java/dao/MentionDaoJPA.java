package dao;

import domain.Mention;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jordy on 04/10/2017.
 */
@Stateless @JPA
public class MentionDaoJPA extends DaoFacade<Mention> {

    @PersistenceContext(unitName = "kwetter-local-pu")
    private EntityManager em;

    public MentionDaoJPA() {
        super(Mention.class);
    }

    public Collection<Mention> getMentions(String username) {
        Collection<Mention> mentions =  em.createNamedQuery("mention.findByUsername", Mention.class)
                .setParameter("username", username)
                .getResultList();
        return mentions;
    }

    protected EntityManager getEntityManager() {
        return this.em;
    }
}
