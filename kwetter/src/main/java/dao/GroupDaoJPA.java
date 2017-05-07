package dao;

import domain.Group;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by jordy on 3/14/2017.
 */
@Stateless
@JPA
public class GroupDaoJPA extends DaoFacade<Group> {

    @PersistenceContext(unitName = "kwetter-local-pu")
    private EntityManager em;

    public GroupDaoJPA() {
        super(Group.class);
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
