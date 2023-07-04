package com.uio443.diarybackend.model;

import com.uio443.diarybackend.enums.HiddenStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String pfpLink;
    private int age;
    private HiddenStatus hiddenStatus;

    public User() { }

    public User(String username, String email, String pfpLink, int age, HiddenStatus hiddenStatus) {
        this.username = username;
        this.email = email;
        this.pfpLink = pfpLink;
        this.age = age;
        this.hiddenStatus = hiddenStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPfpLink() {
        return pfpLink;
    }

    public void setPfpLink(String pfpLink) {
        this.pfpLink = pfpLink;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HiddenStatus getHiddenStatus() {
        return hiddenStatus;
    }

    public void setHiddenStatus(HiddenStatus hiddenStatus) {
        this.hiddenStatus = hiddenStatus;
    }
}
