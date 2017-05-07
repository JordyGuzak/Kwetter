package domain;

import domain.Kweet;
import domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20160829-rNA", date="2017-05-07T14:07:38")
@StaticMetamodel(Mention.class)
public class Mention_ { 

    public static volatile SingularAttribute<Mention, Kweet> kweet;
    public static volatile SingularAttribute<Mention, Long> id;
    public static volatile SingularAttribute<Mention, User> user;

}