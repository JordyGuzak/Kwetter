package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Jordy on 15-2-2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "kweet.getRecentKweets", query = "SELECT k FROM Kweet as k WHERE k.owner = :user order by k.postDate desc"),
        @NamedQuery(name = "kweet.getTimeLine",
                query = "SELECT k " +
                        "FROM Kweet k  " +
                        "WHERE k.owner = :user OR k.owner IN (" +
                        "SELECT DISTINCT u " +
                        "FROM User u JOIN u.followers r " +
                        "WHERE r = :user) " +
                        "ORDER BY k.postDate DESC"),
        @NamedQuery(name = "kweet.getKweetCount", query = "SELECT COUNT(k) FROM Kweet k WHERE k.owner = :user"),
        @NamedQuery(name = "kweet.searchKweets", query = "SELECT k FROM Kweet k WHERE k.owner = :user AND k.message LIKE :query")
})
@JsonIgnoreProperties({"likedByUsers", "owner"})
public class Kweet implements Serializable {

    @Id @GeneratedValue
    private Long id;

    @Transient
    private String username;

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "KWEETS_LIKES",
            joinColumns = @JoinColumn(name = "KWETTER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"))
    private Collection<User> likedByUsers;

    public Kweet() {
    }

    public Kweet(User owner, String message) {
        if (owner == null || message == null || message.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.owner = owner;
        this.message = message;
        this.postDate = new Date();
        this.likedByUsers = new ArrayList<User>();
    }

    public void likeKweet(User user) {
        if (!this.likedByUsers.contains(user) && !user.equals(owner)) {
            this.likedByUsers.add(user);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @XmlTransient
    public Collection<User> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(Collection<User> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
