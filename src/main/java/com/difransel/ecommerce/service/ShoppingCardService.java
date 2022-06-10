package com.difransel.ecommerce.service;


import com.difransel.ecommerce.model.ShoppingCard;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ShoppingCard}.
 */
public interface ShoppingCardService {
    /**
     * Save a shoppingCard.
     *
     * @param shoppingCard the entity to save.
     * @return the persisted entity.
     */
    ShoppingCard save(ShoppingCard shoppingCard);

    /**
     * Updates a shoppingCard.
     *
     * @param shoppingCard the entity to update.
     * @return the persisted entity.
     */
    ShoppingCard update(ShoppingCard shoppingCard);

    /**
     * Partially updates a shoppingCard.
     *
     * @param shoppingCard the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ShoppingCard> partialUpdate(ShoppingCard shoppingCard);

    /**
     * Get all the shoppingCards.
     *
     * @return the list of entities.
     */
    List<ShoppingCard> findAll();

    /**
     * Get the "id" shoppingCard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShoppingCard> findOne(Long id);

    /**
     * Delete the "id" shoppingCard.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
