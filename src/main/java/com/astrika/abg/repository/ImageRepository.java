package com.astrika.abg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.astrika.abg.model.ImageMaster;


public interface ImageRepository extends JpaRepository<ImageMaster, Long> {
	
	//@Query("select i from ImageMaster i left join fetch i.createdBy u left join fetch u.document d left join fetch u.city c left join fetch u.state s left join fetch u.country c where i.imageId=?1")
	ImageMaster findByImageId(Long imageId);
	
	
	

}
