package com.difransel.ecommerce.repository;

import com.difransel.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the User entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
