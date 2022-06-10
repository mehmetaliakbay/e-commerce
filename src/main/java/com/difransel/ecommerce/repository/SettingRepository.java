package com.difransel.ecommerce.repository;

import com.difransel.ecommerce.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Setting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {}
