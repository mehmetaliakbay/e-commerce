package com.difransel.ecommerce.service;


import com.difransel.ecommerce.domain.CustomerOrder;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CustomerOrder}.
 */
public interface OrderService {
    /**
     * Save a order.
     *
     * @param customerOrder the entity to save.
     * @return the persisted entity.
     */
    CustomerOrder save(CustomerOrder customerOrder);

    /**
     * Updates a order.
     *
     * @param customerOrder the entity to update.
     * @return the persisted entity.
     */
    CustomerOrder update(CustomerOrder customerOrder);

    /**
     * Partially updates a order.
     *
     * @param customerOrder the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CustomerOrder> partialUpdate(CustomerOrder customerOrder);

    /**
     * Get all the orders.
     *
     * @return the list of entities.
     */
    List<CustomerOrder> findAll();

    /**
     * Get the "id" order.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomerOrder> findOne(Long id);

    /**
     * Delete the "id" order.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
