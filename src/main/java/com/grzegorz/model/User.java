package com.grzegorz.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Grześ on 2017-04-03.
 */
@Entity
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min = 3, max = 50)
    private String firstName;
    @Size(min = 3, max = 50)
    private String lastName;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Size(min = 5)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Advertisement> Advertisements;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Advertisement> getAdvertisements() {
        return Advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        Advertisements = advertisements;
    }
}
