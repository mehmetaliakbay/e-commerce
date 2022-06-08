package com.difransel.ecommerce.web.rest;

import com.difransel.ecommerce.domain.ShoppingCard;
import com.difransel.ecommerce.repository.ShoppingCardRepository;
import com.difransel.ecommerce.service.ShoppingCardService;
import com.difransel.ecommerce.web.rest.errors.BadRequestAlertException;
import com.difransel.ecommerce.web.util.HeaderUtil;
import com.difransel.ecommerce.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link ShoppingCard}.
 */
@RestController
@RequestMapping("/api")
public class ShoppingCardResource {

    private final Logger log = LoggerFactory.getLogger(ShoppingCardResource.class);

    private static final String ENTITY_NAME = "shoppingCard";

    @Value("${spring.application.name}")
    private String applicationName;

    private final ShoppingCardService shoppingCardService;

    private final ShoppingCardRepository shoppingCardRepository;

    public ShoppingCardResource(ShoppingCardService shoppingCardService, ShoppingCardRepository shoppingCardRepository) {
        this.shoppingCardService = shoppingCardService;
        this.shoppingCardRepository = shoppingCardRepository;
    }

    /**
     * {@code POST  /shopping-cards} : Create a new shoppingCard.
     *
     * @param shoppingCard the shoppingCard to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shoppingCard, or with status {@code 400 (Bad Request)} if the shoppingCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shopping-cards")
    public ResponseEntity<ShoppingCard> createShoppingCard(@RequestBody ShoppingCard shoppingCard) throws URISyntaxException {
        log.debug("REST request to save ShoppingCard : {}", shoppingCard);
        if (shoppingCard.getId() != null) {
            throw new BadRequestAlertException("A new shoppingCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShoppingCard result = shoppingCardService.save(shoppingCard);
        return ResponseEntity
            .created(new URI("/api/shopping-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shopping-cards/:id} : Updates an existing shoppingCard.
     *
     * @param id the id of the shoppingCard to save.
     * @param shoppingCard the shoppingCard to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shoppingCard,
     * or with status {@code 400 (Bad Request)} if the shoppingCard is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shoppingCard couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shopping-cards/{id}")
    public ResponseEntity<ShoppingCard> updateShoppingCard(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShoppingCard shoppingCard
    ) throws URISyntaxException {
        log.debug("REST request to update ShoppingCard : {}, {}", id, shoppingCard);
        if (shoppingCard.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shoppingCard.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shoppingCardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ShoppingCard result = shoppingCardService.update(shoppingCard);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shoppingCard.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /shopping-cards/:id} : Partial updates given fields of an existing shoppingCard, field will ignore if it is null
     *
     * @param id the id of the shoppingCard to save.
     * @param shoppingCard the shoppingCard to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shoppingCard,
     * or with status {@code 400 (Bad Request)} if the shoppingCard is not valid,
     * or with status {@code 404 (Not Found)} if the shoppingCard is not found,
     * or with status {@code 500 (Internal Server Error)} if the shoppingCard couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/shopping-cards/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ShoppingCard> partialUpdateShoppingCard(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShoppingCard shoppingCard
    ) throws URISyntaxException {
        log.debug("REST request to partial update ShoppingCard partially : {}, {}", id, shoppingCard);
        if (shoppingCard.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shoppingCard.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shoppingCardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ShoppingCard> result = shoppingCardService.partialUpdate(shoppingCard);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shoppingCard.getId().toString())
        );
    }

    /**
     * {@code GET  /shopping-cards} : get all the shoppingCards.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shoppingCards in body.
     */
    @GetMapping("/shopping-cards")
    public List<ShoppingCard> getAllShoppingCards() {
        log.debug("REST request to get all ShoppingCards");
        return shoppingCardService.findAll();
    }

    /**
     * {@code GET  /shopping-cards/:id} : get the "id" shoppingCard.
     *
     * @param id the id of the shoppingCard to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shoppingCard, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shopping-cards/{id}")
    public ResponseEntity<ShoppingCard> getShoppingCard(@PathVariable Long id) {
        log.debug("REST request to get ShoppingCard : {}", id);
        Optional<ShoppingCard> shoppingCard = shoppingCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shoppingCard);
    }

    /**
     * {@code DELETE  /shopping-cards/:id} : delete the "id" shoppingCard.
     *
     * @param id the id of the shoppingCard to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shopping-cards/{id}")
    public ResponseEntity<Void> deleteShoppingCard(@PathVariable Long id) {
        log.debug("REST request to delete ShoppingCard : {}", id);
        shoppingCardService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
