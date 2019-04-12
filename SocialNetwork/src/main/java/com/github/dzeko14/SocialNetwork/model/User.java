package com.github.dzeko14.SocialNetwork.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Usr")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    @NotNull
    private String password;

    public User() {
    }

    public User(long id, String name, String surname, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public static User merge(User oldOne, User newOne){
        String name = newOne.getName() == null ? oldOne.getName() : newOne.getName();
        String surname = newOne.surname == null ? oldOne.surname : newOne.surname;
        String password = newOne.password  == null ? oldOne.password : newOne.password;
        String email = newOne.email  == null ? oldOne.email : newOne.email;

        return new User(oldOne.id, name, surname, email, password);
    }
}
