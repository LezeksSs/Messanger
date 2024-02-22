package com.example.MessangerServer.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "text")
    private String text;
    @Column(name = "sending_date")
    @CreationTimestamp
    private Date sending_date;
    @Column(name = "sending_status")
    private boolean sending_status;
    @Column(name = "read_status")
    private boolean read_status;
    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdOn;
    @Column(name = "modified_date")
    @UpdateTimestamp
    private Date modifiedOn;

    public Message() {
    }

    public Message(String text, Date sending_date, boolean sending_status, boolean read_status, Date createdOn, Date modifiedOn) {
        this.text = text;
        this.sending_date = sending_date;
        this.sending_status = sending_status;
        this.read_status = read_status;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSending_date() {
        return sending_date;
    }

    public void setSending_date(Date sending_date) {
        this.sending_date = sending_date;
    }

    public boolean isSending_status() {
        return sending_status;
    }

    public void setSending_status(boolean sending_status) {
        this.sending_status = sending_status;
    }

    public boolean isRead_status() {
        return read_status;
    }

    public void setRead_status(boolean read_status) {
        this.read_status = read_status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
