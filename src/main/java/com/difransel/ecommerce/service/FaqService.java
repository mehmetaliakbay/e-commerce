package com.difransel.ecommerce.service;


import com.difransel.ecommerce.model.Faq;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Faq}.
 */
public interface FaqService {
    /**
     * Save a faq.
     *
     * @param faq the entity to save.
     * @return the persisted entity.
     */
    Faq save(Faq faq);

    /**
     * Updates a faq.
     *
     * @param faq the entity to update.
     * @return the persisted entity.
     */
    Faq update(Faq faq);

    /**
     * Partially updates a faq.
     *
     * @param faq the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Faq> partialUpdate(Faq faq);

    /**
     * Get all the faqs.
     *
     * @return the list of entities.
     */
    List<Faq> findAll();

    /**
     * Get the "id" faq.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Faq> findOne(Long id);

    /**
     * Delete the "id" faq.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
