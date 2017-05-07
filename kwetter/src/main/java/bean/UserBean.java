package bean;

import domain.User;
import service.UserService;
import util.PasswordHash;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by jordy on 3/12/2017.
 */
@Named(value="userBean")
@RequestScoped
public class UserBean implements Serializable {

    @Inject
    private UserService userService;

    private String name;
    private String email;
    private Integer age;
    private String password;


    public void addUser() {
        User user = new User(name, age, email, PasswordHash.stringToHash(password));
        userService.addUser(user);
    }

    public void removeUser(User user) {
        userService.removeUser(user);
    }

    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
