package com.authorization.authentication.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "chat")
@Getter @Setter @ToString
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Users userOne;

    @OneToMany(targetEntity = Messages.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "chats_messages", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "message_id"))
    @JsonIgnore
    private List<Messages> messages;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Users userTwo;

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

    public Chat() {
        // Constructor
    }
}