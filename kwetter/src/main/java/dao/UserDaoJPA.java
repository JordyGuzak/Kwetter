package dao;

import domain.Group;
import domain.Kweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jordy on 15-2-2017.
 */
@Stateless @JPA
public class UserDaoJPA extends DaoFacade<User> implements UserDao {

    @PersistenceContext(unitName = "kwetter-local-pu")
    private EntityManager em;

    public UserDaoJPA(){ super(User.class); }

    public User logIn(String username, String password) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
                .setParameter("username", username)
                .setParameter("password", password);
        
        return (User) query.getSingleResult();
    }

    public void update(User user) {
        edit(user);
    }

    public void addUser(User user) {
        if (user.getGroups().isEmpty()) {
            Group userGroup = findGroupByName("UserGroup");
            if (userGroup != null) {
                user.getGroups().add(userGroup);
            }
        }
        create(user);
    }

    public void removeUser(User user) {
        User u = em.merge(user);
        u.clearGroups();
        this.remove(user);
    }

    public void followUser(User user, User userToFollow) {
        User user1 = find(user.getId());
        User user2 = find(userToFollow.getId());
        user1.addFollowing(user2);
        user2.addFollower(user1);
    }

    public void unFollowUser(User user, User userToUnFollow) {
        User user1 = find(user.getId());
        User user2 = find(userToUnFollow.getId());
        user1.removeFollowing(user2);
        user2.removeFollower(user1);
    }

    public User findUserByName(String name) {
        TypedQuery<User> query = em.createNamedQuery("user.findByName", User.class);
        query.setParameter("name", name);
        List<User> result = query.getResultList();
        return !result.isEmpty() ? result.get(0) : null;
    }

    public User findUserById(Long id) {
        return find(id);
    }

    public Collection<User> getUsers() {
        return em.createNamedQuery("user.getAllUsers", User.class).getResultList();
    }

    public Collection<User> getFollowers(Long userId) {
        if (userId != null) {
            User user = find(userId);
            if (user != null) {
                return user.getFollowers();
            }
        }
        return null;
    }

    public Collection<User> getFollowing(Long userId) {
        if (userId != null) {
            User user = find(userId);
            if (user != null) {
                return user.getFollowing();
            }
        }
        return null;
    }

    public Group findGroupByName(String groupName) {
        TypedQuery<Group> query = em.createNamedQuery("group.findByName", Group.class);
        query.setParameter("groupName", groupName);
        List<Group> result = query.getResultList();
        return !result.isEmpty() ? result.get(0) : null;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
