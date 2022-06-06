package com.difransel.ecommerce.service;


import com.difransel.ecommerce.domain.OrderProduct;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link OrderProduct}.
 */
public interface OrderProductService {
    /**
     * Save a orderProduct.
     *
     * @param orderProduct the entity to save.
     * @return the persisted entity.
     */
    OrderProduct save(OrderProduct orderProduct);

    /**
     * Updates a orderProduct.
     *
     * @param orderProduct the entity to update.
     * @return the persisted entity.
     */
    OrderProduct update(OrderProduct orderProduct);

    /**
     * Partially updates a orderProduct.
     *
     * @param orderProduct the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OrderProduct> partialUpdate(OrderProduct orderProduct);

    /**
     * Get all the orderProducts.
     *
     * @return the list of entities.
     */
    List<OrderProduct> findAll();

    /**
     * Get the "id" orderProduct.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderProduct> findOne(Long id);

    /**
     * Delete the "id" orderProduct.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
