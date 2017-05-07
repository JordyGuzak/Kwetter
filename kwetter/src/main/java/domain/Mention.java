package domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jordy
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "mention.findByUsername", query = "SELECT m FROM Mention m WHERE m.user.username = :username"),
    @NamedQuery(name = "mention.findById", query = "SELECT m from Mention m WHERE m.id = :id")})
@XmlRootElement
public class Mention implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Kweet kweet;

    @ManyToOne
    private User user;

    public Mention() {
    }

    public Mention(Kweet kweet, User user) {
        this.kweet = kweet;
        this.user = user;
    }

    public Kweet getKweet() {
        return kweet;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
