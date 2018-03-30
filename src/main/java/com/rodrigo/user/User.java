package com.rodrigo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rodrigo.core.BaseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User extends BaseEntity{

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private String firstName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String[] roles;

    protected User() {
        super();
    }

    public User(  String password, String username, String firstName, String lastName, String[] roles) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        setPassword(password);
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    private void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public static PasswordEncoder getPasswordEncoder() {
        return PASSWORD_ENCODER;
    }

    public String getPassword() {
        return password;
    }

    public String[] getRoles() {
        return roles;
    }
}
