package com.difransel.ecommerce.repository;

import com.difransel.ecommerce.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Images entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {}
