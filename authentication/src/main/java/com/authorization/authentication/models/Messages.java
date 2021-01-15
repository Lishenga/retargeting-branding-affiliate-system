package com.authorization.authentication.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.authorization.authentication.requests.messages.CreateMessageRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "messages")
@Getter @Setter @ToString
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Users sender;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Users receiver;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Chat chat;

    @Column(name = "read_sender", nullable = false)
    private Boolean readSender;

    @Column(name = "read_receiver", nullable = false)
    private Boolean readReceiver;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @OneToOne(fetch = FetchType.EAGER)
    private Users createdBy;

    @OneToOne(fetch = FetchType.EAGER)
    private Users updatedBy;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Messages() {
        // Constructor
    }

    public void createMessage(CreateMessageRequest createMessageRequest) {
        this.message = createMessageRequest.getMessage();
        this.readReceiver = createMessageRequest.getReadReceiver();
        this.readSender = createMessageRequest.getReadSender();
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}