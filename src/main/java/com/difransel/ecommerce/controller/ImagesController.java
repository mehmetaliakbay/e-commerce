package com.difransel.ecommerce.controller;

import com.difransel.ecommerce.model.Images;
import com.difransel.ecommerce.repository.ImagesRepository;
import com.difransel.ecommerce.service.ImagesService;
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
 * REST controller for managing {@link Images}.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ImagesController {


    private static final String ENTITY_NAME = "images";

    @Value("${spring.application.name}")
    private String applicationName;

    private final ImagesService imagesService;

    private final ImagesRepository imagesRepository;

    public ImagesController(ImagesService imagesService, ImagesRepository imagesRepository) {
        this.imagesService = imagesService;
        this.imagesRepository = imagesRepository;
    }

    /**
     * {@code POST  /images} : Create a new images.
     *
     * @param images the images to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new images, or with status {@code 400 (Bad Request)} if the images has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/images")
    public ResponseEntity<Images> createImages(@RequestBody Images images) throws URISyntaxException {
        log.debug("REST request to save Images : {}", images);
        if (images.getId() != null) {
            throw new BadRequestAlertException("A new images cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Images result = imagesService.save(images);
        return ResponseEntity
            .created(new URI("/api/images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /images/:id} : Updates an existing images.
     *
     * @param id the id of the images to save.
     * @param images the images to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated images,
     * or with status {@code 400 (Bad Request)} if the images is not valid,
     * or with status {@code 500 (Internal Server Error)} if the images couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/images/{id}")
    public ResponseEntity<Images> updateImages(@PathVariable(value = "id", required = false) final Long id, @RequestBody Images images)
        throws URISyntaxException {
        log.debug("REST request to update Images : {}, {}", id, images);
        if (images.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, images.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!imagesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Images result = imagesService.update(images);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, images.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /images/:id} : Partial updates given fields of an existing images, field will ignore if it is null
     *
     * @param id the id of the images to save.
     * @param images the images to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated images,
     * or with status {@code 400 (Bad Request)} if the images is not valid,
     * or with status {@code 404 (Not Found)} if the images is not found,
     * or with status {@code 500 (Internal Server Error)} if the images couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/images/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Images> partialUpdateImages(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Images images
    ) throws URISyntaxException {
        log.debug("REST request to partial update Images partially : {}, {}", id, images);
        if (images.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, images.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!imagesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Images> result = imagesService.partialUpdate(images);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, images.getId().toString())
        );
    }

    /**
     * {@code GET  /images} : get all the images.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of images in body.
     */
    @GetMapping("/images")
    public List<Images> getAllImages() {
        log.debug("REST request to get all Images");
        return imagesService.findAll();
    }

    /**
     * {@code GET  /images/:id} : get the "id" images.
     *
     * @param id the id of the images to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the images, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/images/{id}")
    public ResponseEntity<Images> getImages(@PathVariable Long id) {
        log.debug("REST request to get Images : {}", id);
        Optional<Images> images = imagesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(images);
    }

    /**
     * {@code DELETE  /images/:id} : delete the "id" images.
     *
     * @param id the id of the images to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/images/{id}")
    public ResponseEntity<Void> deleteImages(@PathVariable Long id) {
        log.debug("REST request to delete Images : {}", id);
        imagesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
