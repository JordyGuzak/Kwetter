package domain;

import domain.Group;
import domain.Kweet;
import domain.Mention;
import domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20160829-rNA", date="2017-04-16T14:26:12")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> website;
    public static volatile CollectionAttribute<User, Group> groups;
    public static volatile SingularAttribute<User, String> biography;
    public static volatile SingularAttribute<User, String> photoUrl;
    public static volatile SingularAttribute<User, String> password;
    public static volatile CollectionAttribute<User, User> followers;
    public static volatile CollectionAttribute<User, User> following;
    public static volatile CollectionAttribute<User, Mention> mentions;
    public static volatile CollectionAttribute<User, Kweet> kweets;
    public static volatile SingularAttribute<User, String> location;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Integer> age;
    public static volatile SingularAttribute<User, String> username;

}