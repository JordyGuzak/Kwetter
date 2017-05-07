package model;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jordy on 05/07/2017.
 */
public class ResultLinks {

    private boolean success;
    private Collection<Link> links = new ArrayList<>();

    public ResultLinks() {
    }

    public void addLink(Link link) {
        this.links.add(link);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Collection<Link> getLinks() {
        return links;
    }

    public void setLinks(Collection<Link> links) {
        this.links = links;
    }
}
