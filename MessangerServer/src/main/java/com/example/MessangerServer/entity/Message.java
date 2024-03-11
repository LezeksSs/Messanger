package com.example.MessangerServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "text")
    @Size(max = 1000)
    private String text;
    @Column(name = "sending_date")
    @CreationTimestamp
    private Date sending_date;
    @Column(name = "sending_status")
    private boolean sending_status;
    @Column(name = "read_status")
    private boolean read_status;
    @JsonIgnore
    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdOn;
    @JsonIgnore
    @Column(name = "modified_date")
    @UpdateTimestamp
    private Date modifiedOn;

}
