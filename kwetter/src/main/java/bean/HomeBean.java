package bean;

import domain.User;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by jordy on 04/04/2017.
 */
@Named(value = "homeBean")
@ViewScoped
public class HomeBean implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private AuthBean authBean;

    private User user;

    @PostConstruct
    public void onLoad() {
        loadUser();
    }

    public void loadUser() {
        this.user = userService.findUserByName(authBean.getPrincipalName());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
