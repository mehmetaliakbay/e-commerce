package com.difransel.ecommerce.service;


import com.difransel.ecommerce.domain.UserA;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link UserA}.
 */
public interface UserAService {
    /**
     * Save a userA.
     *
     * @param userA the entity to save.
     * @return the persisted entity.
     */
    UserA save(UserA userA);

    /**
     * Updates a userA.
     *
     * @param userA the entity to update.
     * @return the persisted entity.
     */
    UserA update(UserA userA);

    /**
     * Partially updates a userA.
     *
     * @param userA the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserA> partialUpdate(UserA userA);

    /**
     * Get all the userAS.
     *
     * @return the list of entities.
     */
    List<UserA> findAll();

    /**
     * Get the "id" userA.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserA> findOne(Long id);

    /**
     * Delete the "id" userA.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
