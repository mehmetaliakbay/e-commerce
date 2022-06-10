package com.difransel.ecommerce.controller;

import com.difransel.ecommerce.model.User;
import com.difransel.ecommerce.repository.UserRepository;
import com.difransel.ecommerce.service.UserService;
import com.difransel.ecommerce.exception.BadRequestAlertException;
import com.difransel.ecommerce.util.HeaderUtil;
import com.difransel.ecommerce.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link User}.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {


    private static final String ENTITY_NAME = "user";

    @Value("${spring.application.name}")
    private String applicationName;

    private final UserService userService;

    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * {@code POST  /user-as} : Create a new user.
     *
     * @param user the user to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user, or with status {@code 400 (Bad Request)} if the user has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-as")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        log.debug("REST request to save User : {}", user);
        if (user.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", ENTITY_NAME, "idexists");
        }
        User result = userService.save(user);
        return ResponseEntity
            .created(new URI("/api/user-as/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-as/:id} : Updates an existing user.
     *
     * @param id the id of the user to save.
     * @param user the user to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user,
     * or with status {@code 400 (Bad Request)} if the user is not valid,
     * or with status {@code 500 (Internal Server Error)} if the user couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-as/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id", required = false) final Long id, @RequestBody User user)
        throws URISyntaxException {
        log.debug("REST request to update User : {}, {}", id, user);
        if (user.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, user.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        User result = userService.update(user);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, user.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-as/:id} : Partial updates given fields of an existing user, field will ignore if it is null
     *
     * @param id the id of the user to save.
     * @param user the user to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user,
     * or with status {@code 400 (Bad Request)} if the user is not valid,
     * or with status {@code 404 (Not Found)} if the user is not found,
     * or with status {@code 500 (Internal Server Error)} if the user couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-as/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<User> partialUpdateUser(@PathVariable(value = "id", required = false) final Long id, @RequestBody User user)
        throws URISyntaxException {
        log.debug("REST request to partial update User partially : {}, {}", id, user);
        if (user.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, user.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<User> result = userService.partialUpdate(user);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, user.getId().toString())
        );
    }

    /**
     * {@code GET  /user-as} : get all the users
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of users in body.
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        log.debug("REST request to get all Users");
        return userService.findAll();
    }

    /**
     * {@code GET  /users/:id} : get the "id" user.
     *
     * @param id the id of the user to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the user, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        Optional<User> user = userService.findOne(id);
        return ResponseUtil.wrapOrNotFound(user);
    }

    /**
     * {@code DELETE  /user-as/:id} : delete the "id" user.
     *
     * @param id the id of the user to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-as/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete User : {}", id);
        userService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
