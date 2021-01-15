package com.retargeting_branding.repositories;

import com.retargeting_branding.enums.TagsStatus;

import java.util.List;

import com.retargeting_branding.enums.Generation;
import com.retargeting_branding.models.Advert;
import com.retargeting_branding.models.Impression;
import com.retargeting_branding.models.Tags;
import com.retargeting_branding.utils.GetTags;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagsRepository extends JpaRepository<Tags, Long> {
    
    Page<Tags> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Page<Tags> findByTagsStatus(TagsStatus tagsStatus, Pageable pageable);

    Page<Tags> findByGeneration(Generation generation, Pageable pageable);

    Page<Tags> findByAdvert(Advert advert, Pageable pageable);

    Page<Tags> findByImpression(Impression impress, Pageable pageable);

    @Query(value = "CALL getTags(:deviceId);", nativeQuery = true)
    List<GetTags> getTags(@Param("deviceId") String deviceId);
}