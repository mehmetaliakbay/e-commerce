package com.difransel.ecommerce.repository;

import com.difransel.ecommerce.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Faq entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {}
