package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// import com.authorization.authentication.requests.clients.CreateClientRequest;

@Entity
@Table(name = "clients")
@Getter @Setter @ToString
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Column(name = "nick_name", nullable = true)
    private String nickName;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users createdBy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users updatedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Clients() {
        // Constructor
    }

    // public void createClient(CreateClientRequest createClientRequest) {
    //     this.clientId = createClientRequest.getClientId();
    //     this.clientSecret = createClientRequest.getClientSecret();
    //     if(createClientRequest.getNickName() != null){
    //         this.nickName = createClientRequest.getNickName();
    //     }
    //     this.isDeleted = false;
    //     this.createdAt = LocalDateTime.now();
    //     this.updatedAt = LocalDateTime.now();
    // }

}