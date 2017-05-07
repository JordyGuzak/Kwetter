package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by jordy on 3/12/2017.
 */
@Entity(name = "GROUPS")
@NamedQueries({
        @NamedQuery(name = "group.findByName", query = "SELECT g FROM GROUPS g WHERE g.groupName = :groupName")
})
public class Group implements Serializable {

    public static final String ADMIN_GROUP="AdminGroup", USER_GROUP="UserGroup";

    @Id
    private String groupName;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
