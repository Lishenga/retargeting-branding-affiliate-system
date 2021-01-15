package com.retargeting_branding.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retargeting_branding.enums.Generation;
import com.retargeting_branding.enums.TagsStatus;
import com.retargeting_branding.requests.tags.CreateTagRequest;
import com.retargeting_branding.requests.tags.UpdateTagRequest;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "tags")
@Getter @Setter @ToString
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "Tags.getTags", procedureName = "getTags", parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "deviceId", type = String.class)})})
public class Tags {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private Generation generation;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private TagsStatus tagsStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Impression impression;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore 
    private Advert advert;

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

    public Tags(){}

    public Tags(Tags tags) {
        this.id = tags.id;
        this.name = tags.name;
        this.description = tags.description;
        this.generation = tags.generation;
        this.tagsStatus = tags.tagsStatus;
        this.impression = tags.impression;
        this.advert = tags.advert;
        this.isDeleted = tags.isDeleted;
        this.deletedAt = tags.deletedAt;
        this.createdBy = tags.createdBy;
        this.updatedBy = tags.updatedBy;
        this.createdAt = tags.createdAt;
        this.updatedAt = tags.updatedAt;
    }

    public void createTag(CreateTagRequest createTagRequest){

        this.name = createTagRequest.getName();
        if(createTagRequest.getDescription() != null){
            this.description = createTagRequest.getDescription();
        }
        this.generation = createTagRequest.getGenerationType();
        this.tagsStatus = createTagRequest.getTagStatus();
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateTag(UpdateTagRequest updateTagRequest){

        if(updateTagRequest.getName() != null){
            this.setName(updateTagRequest.getName());
        }

        if(updateTagRequest.getDescription() != null){
            this.setDescription(updateTagRequest.getDescription());
        }

        if(updateTagRequest.getGenerationType() != null){
            this.setGeneration(updateTagRequest.getGenerationType());
        }

        if(updateTagRequest.getTagStatus() != null){
            this.setTagsStatus(updateTagRequest.getTagStatus());
        }

        this.setUpdatedAt(LocalDateTime.now());
    }
}