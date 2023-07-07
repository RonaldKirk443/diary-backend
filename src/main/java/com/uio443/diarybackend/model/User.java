package com.uio443.diarybackend.model;

import com.uio443.diarybackend.enums.HiddenStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String username;
    private String email;
    private String pfpLink;
    private Date birthdate;
    private HiddenStatus hiddenStatus;

    public User() {
        this.hiddenStatus = HiddenStatus.Private;
    }


    public Long getId() {
        return id;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public HiddenStatus getHiddenStatus() {
        return hiddenStatus;
    }

    public void setHiddenStatus(HiddenStatus hiddenStatus) {
        this.hiddenStatus = hiddenStatus;
    }
}
