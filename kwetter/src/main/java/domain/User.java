package domain;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jordy on 15-2-2017.
 */

@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = "user.findByName", query = "SELECT u FROM User u WHERE u.username = :name"),
        @NamedQuery(name = "user.getAllUsers", query = "SELECT u FROM User u")
})
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private Integer age;
    private String location;
    private String website;
    private String photoUrl;
    private String biography;
    @Transient
    private String token;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_RELATIONS",
            joinColumns = @JoinColumn(name = "FOLLOWED"),
            inverseJoinColumns = @JoinColumn(name = "FOLLOWER"))
    private Collection<User> followers;

    @ManyToMany(mappedBy = "followers")
    private Collection<User> following;

    @OneToMany(mappedBy = "owner")
    @OrderBy("postDate desc")
    private Collection<Kweet> kweets;


    @JoinTable(name = "USERS_GROUPS",
            joinColumns
                    = @JoinColumn(name = "USERNAME", referencedColumnName = "username"),
            inverseJoinColumns
                    = @JoinColumn(name = "GROUPNAME", referencedColumnName = "groupName"))
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Collection<Group> groups;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Mention> mentions;

    @Transient
    private Link self;

    public User() {
    }

    public User(String username, Integer age, String email, String password) {
        if (username == null || username.isEmpty()) throw new IllegalArgumentException();

        if (age == null) throw new IllegalArgumentException();

        if (email == null || email.isEmpty()) throw new IllegalArgumentException();

        if (password == null || password.isEmpty()) throw new IllegalArgumentException();

        this.username = username;
        this.age = age;
        this.email = email;
        this.password = password;
        this.location = "";
        this.website = "";
        this.photoUrl = "";
        this.biography = "";
        this.followers = new ArrayList<User>();
        this.following = new ArrayList<User>();
        this.kweets = new ArrayList<Kweet>();
        this.groups = new ArrayList<Group>();
    }

    public void addMention(Mention mention) {
        this.mentions.add(mention);
    }


    public void addFollower(User follower) {
        if (!this.followers.contains(follower)) {
            this.followers.add(follower);
        }
    }

    public void removeFollower(User follower) {
        this.followers.remove(follower);
    }

    public void addFollowing(User user) {
        if (!this.following.contains(user)) {
            this.following.add(user);
        }
    }

    public void removeFollowing(User user) {
        this.following.remove(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @XmlTransient
    public Collection<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<User> followers) {
        this.followers = followers;
    }

    @XmlTransient
    public Collection<User> getFollowing() {
        return following;
    }

    public void setFollowing(Collection<User> following) {
        this.following = following;
    }

    @XmlTransient
    public Collection<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(Collection<Kweet> kweets) {
        this.kweets = kweets;
    }

    @XmlTransient
    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }

    public void clearGroups() {
        groups.clear();
    }

    public void clearFollowing() { following.clear(); }

    public void clearFollowers() { followers.clear(); }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @XmlTransient
    public Collection<Mention> getMentions() {
        return mentions;
    }

    public void setMentions(Collection<Mention> mentions) {
        this.mentions = mentions;
    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!email.equals(user.email)) return false;
        return groups != null ? groups.equals(user.groups) : user.groups == null;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
