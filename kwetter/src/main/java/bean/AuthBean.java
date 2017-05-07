package bean;

import domain.User;
import service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named(value = "authBean")
@SessionScoped
public class AuthBean implements Serializable {

    @Inject
    private UserService userService;

    private String username;
    private String password;

    public void logIn() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(username, password);
            User user = userService.findUserByName(username);
            externalContext.getSessionMap().put("user", user);
            externalContext.redirect(externalContext.getRequestContextPath() + "/home.xhtml");
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
    }

    public String doLogout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();

        return "index.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
    }

    public String getPrincipalName() {
        if (isLoggedIn()) {
            return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        } else {
            return "ANONYMOUS";
        }
    }

    public boolean isAdmin() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext().isUserInRole("AdminRole");
    }

    public User getCurrentUser() {
        User user = null;
        if (isLoggedIn()) {
            String username = getPrincipalName();
            if (!username.contentEquals("ANONYMOUS")) {
                user = userService.findUserByName(username);
            }
        }
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
