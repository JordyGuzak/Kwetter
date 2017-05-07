package batch;

import util.PasswordHash;

public class InputUser {

    private String name;
    private Integer age;
    private String email;
    private String password;

    public InputUser() {
    }

    public boolean isSet() {
        if (name != null && age != null && email != null && password != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PasswordHash.stringToHash(password);
    }
}
