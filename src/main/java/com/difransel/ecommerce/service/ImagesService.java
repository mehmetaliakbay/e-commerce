package com.difransel.ecommerce.service;


import com.difransel.ecommerce.domain.Images;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Images}.
 */
public interface ImagesService {
    /**
     * Save a images.
     *
     * @param images the entity to save.
     * @return the persisted entity.
     */
    Images save(Images images);

    /**
     * Updates a images.
     *
     * @param images the entity to update.
     * @return the persisted entity.
     */
    Images update(Images images);

    /**
     * Partially updates a images.
     *
     * @param images the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Images> partialUpdate(Images images);

    /**
     * Get all the images.
     *
     * @return the list of entities.
     */
    List<Images> findAll();

    /**
     * Get the "id" images.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Images> findOne(Long id);

    /**
     * Delete the "id" images.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
