package com.uio443.diarybackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uio443.diarybackend.enums.HiddenStatus;
import com.uio443.diarybackend.primarykeys.CollectionUserId;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@IdClass(CollectionUserId.class)
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    Long id;
    @Id
    @JoinColumn(name = "userId", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    User user;
    @Column(nullable = false)
    String title;
    String description;
    String backgroundImgLink;
    @Column(nullable = false)
    HiddenStatus hiddenStatus;


    public Collection() {
        this.title = "Untitled Collection";
        this.hiddenStatus = null;
        this.backgroundImgLink = null;
        this.description = null;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackgroundImgLink() {
        return backgroundImgLink;
    }

    public void setBackgroundImgLink(String backgroundImgLink) {
        this.backgroundImgLink = backgroundImgLink;
    }

    public HiddenStatus getHiddenStatus() {
        return hiddenStatus;
    }

    public void setHiddenStatus(HiddenStatus hiddenStatus) {
        this.hiddenStatus = hiddenStatus;
    }
}
