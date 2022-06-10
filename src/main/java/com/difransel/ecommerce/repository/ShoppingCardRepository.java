package com.difransel.ecommerce.repository;

import com.difransel.ecommerce.model.ShoppingCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ShoppingCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShoppingCardRepository extends JpaRepository<ShoppingCard, Long> {}
