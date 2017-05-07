package domain;

import domain.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20160829-rNA", date="2017-05-07T14:07:38")
@StaticMetamodel(Kweet.class)
public class Kweet_ { 

    public static volatile SingularAttribute<Kweet, User> owner;
    public static volatile SingularAttribute<Kweet, Date> postDate;
    public static volatile SingularAttribute<Kweet, Long> id;
    public static volatile CollectionAttribute<Kweet, User> likedByUsers;
    public static volatile SingularAttribute<Kweet, String> message;

}