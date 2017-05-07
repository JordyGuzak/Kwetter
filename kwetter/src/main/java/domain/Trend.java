/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author Jordy
 */
//@Entity
@XmlRootElement
public class Trend implements Serializable {

    //@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hashtag;

    private Integer hashCount;

    @Temporal(TIMESTAMP)
    private Date firstUsed;

    @Temporal(TIMESTAMP)
    private Date lastUsed;

    @OneToMany
    private Collection<Kweet> kweets;

    public Trend() {
    }

    public Trend(String hashtag) {
        this.hashtag = hashtag;
        this.kweets = new ArrayList<Kweet>();
        this.hashCount = 0;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Integer getHashCount() {
        return hashCount;
    }

    public void setHashCount(Integer hashCount) {
        this.hashCount = hashCount;
    }

    public Date getFirstUsed() {
        return firstUsed;
    }

    public void setFirstUsed(Date firstUsed) {
        this.firstUsed = firstUsed;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(Collection<Kweet> kweetes) {
        this.kweets = kweets;
    }

    public void addKweet(Kweet kweet) {
        if (!this.kweets.contains(kweet)) {
            this.kweets.add(kweet);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trend other = (Trend) obj;
        return (this.hashtag.equals(other.hashtag));
    }
}
