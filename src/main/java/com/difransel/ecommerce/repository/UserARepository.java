package com.difransel.ecommerce.repository;

import com.difransel.ecommerce.domain.UserA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UserA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserARepository extends JpaRepository<UserA, Long> {}
