package com.difransel.ecommerce.web.rest;

import com.difransel.ecommerce.domain.UserA;
import com.difransel.ecommerce.repository.UserARepository;
import com.difransel.ecommerce.service.UserAService;
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
 * REST controller for managing {@link UserA}.
 */
@RestController
@RequestMapping("/api")
public class UserAResource {

    private final Logger log = LoggerFactory.getLogger(UserAResource.class);

    private static final String ENTITY_NAME = "userA";

//    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserAService userAService;

    private final UserARepository userARepository;

    public UserAResource(UserAService userAService, UserARepository userARepository) {
        this.userAService = userAService;
        this.userARepository = userARepository;
    }

    /**
     * {@code POST  /user-as} : Create a new userA.
     *
     * @param userA the userA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userA, or with status {@code 400 (Bad Request)} if the userA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-as")
    public ResponseEntity<UserA> createUserA(@RequestBody UserA userA) throws URISyntaxException {
        log.debug("REST request to save UserA : {}", userA);
        if (userA.getId() != null) {
            throw new BadRequestAlertException("A new userA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserA result = userAService.save(userA);
        return ResponseEntity
            .created(new URI("/api/user-as/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-as/:id} : Updates an existing userA.
     *
     * @param id the id of the userA to save.
     * @param userA the userA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userA,
     * or with status {@code 400 (Bad Request)} if the userA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-as/{id}")
    public ResponseEntity<UserA> updateUserA(@PathVariable(value = "id", required = false) final Long id, @RequestBody UserA userA)
        throws URISyntaxException {
        log.debug("REST request to update UserA : {}, {}", id, userA);
        if (userA.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userA.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userARepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserA result = userAService.update(userA);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userA.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-as/:id} : Partial updates given fields of an existing userA, field will ignore if it is null
     *
     * @param id the id of the userA to save.
     * @param userA the userA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userA,
     * or with status {@code 400 (Bad Request)} if the userA is not valid,
     * or with status {@code 404 (Not Found)} if the userA is not found,
     * or with status {@code 500 (Internal Server Error)} if the userA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-as/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserA> partialUpdateUserA(@PathVariable(value = "id", required = false) final Long id, @RequestBody UserA userA)
        throws URISyntaxException {
        log.debug("REST request to partial update UserA partially : {}, {}", id, userA);
        if (userA.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userA.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userARepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserA> result = userAService.partialUpdate(userA);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userA.getId().toString())
        );
    }

    /**
     * {@code GET  /user-as} : get all the userAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userAS in body.
     */
    @GetMapping("/user-as")
    public List<UserA> getAllUserAS() {
        log.debug("REST request to get all UserAS");
        return userAService.findAll();
    }

    /**
     * {@code GET  /user-as/:id} : get the "id" userA.
     *
     * @param id the id of the userA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userA, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-as/{id}")
    public ResponseEntity<UserA> getUserA(@PathVariable Long id) {
        log.debug("REST request to get UserA : {}", id);
        Optional<UserA> userA = userAService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userA);
    }

    /**
     * {@code DELETE  /user-as/:id} : delete the "id" userA.
     *
     * @param id the id of the userA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-as/{id}")
    public ResponseEntity<Void> deleteUserA(@PathVariable Long id) {
        log.debug("REST request to delete UserA : {}", id);
        userAService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
