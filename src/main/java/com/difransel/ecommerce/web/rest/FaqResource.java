package com.difransel.ecommerce.web.rest;

import com.difransel.ecommerce.domain.Faq;
import com.difransel.ecommerce.repository.FaqRepository;
import com.difransel.ecommerce.service.FaqService;
import com.difransel.ecommerce.web.rest.errors.BadRequestAlertException;
import com.difransel.ecommerce.web.util.HeaderUtil;
import com.difransel.ecommerce.web.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
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
 * REST controller for managing {@link Faq}.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FaqResource {

    private final Logger log = LoggerFactory.getLogger(FaqResource.class);

    private static final String ENTITY_NAME = "faq";

    @Value("${spring.application.name}")
    private String applicationName;

    private final FaqService faqService;
    private final FaqRepository faqRepository;


    /**
     * {@code POST  /faqs} : Create a new faq.
     *
     * @param faq the faq to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new faq, or with status {@code 400 (Bad Request)} if the faq has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/faqs")
    public ResponseEntity<Faq> createFaq(@RequestBody Faq faq) throws URISyntaxException {
        log.debug("REST request to save Faq : {}", faq);
        if (faq.getId() != null) {
            throw new BadRequestAlertException("A new faq cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Faq result = faqService.save(faq);
        return ResponseEntity
            .created(new URI("/api/faqs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /faqs/:id} : Updates an existing faq.
     *
     * @param id the id of the faq to save.
     * @param faq the faq to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated faq,
     * or with status {@code 400 (Bad Request)} if the faq is not valid,
     * or with status {@code 500 (Internal Server Error)} if the faq couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/faqs/{id}")
    public ResponseEntity<Faq> updateFaq(@PathVariable(value = "id", required = false) final Long id, @RequestBody Faq faq)
        throws URISyntaxException {
        log.debug("REST request to update Faq : {}, {}", id, faq);
        if (faq.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, faq.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!faqRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Faq result = faqService.update(faq);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, faq.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /faqs/:id} : Partial updates given fields of an existing faq, field will ignore if it is null
     *
     * @param id the id of the faq to save.
     * @param faq the faq to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated faq,
     * or with status {@code 400 (Bad Request)} if the faq is not valid,
     * or with status {@code 404 (Not Found)} if the faq is not found,
     * or with status {@code 500 (Internal Server Error)} if the faq couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/faqs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Faq> partialUpdateFaq(@PathVariable(value = "id", required = false) final Long id, @RequestBody Faq faq)
        throws URISyntaxException {
        log.debug("REST request to partial update Faq partially : {}, {}", id, faq);
        if (faq.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, faq.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!faqRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Faq> result = faqService.partialUpdate(faq);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, faq.getId().toString())
        );
    }

    /**
     * {@code GET  /faqs} : get all the faqs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of faqs in body.
     */
    @GetMapping("/faqs")
    public List<Faq> getAllFaqs() {
        log.debug("REST request to get all Faqs");
        return faqService.findAll();
    }

    /**
     * {@code GET  /faqs/:id} : get the "id" faq.
     *
     * @param id the id of the faq to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the faq, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/faqs/{id}")
    public ResponseEntity<Faq> getFaq(@PathVariable Long id) {
        log.debug("REST request to get Faq : {}", id);
        Optional<Faq> faq = faqService.findOne(id);
        return ResponseUtil.wrapOrNotFound(faq);
    }

    /**
     * {@code DELETE  /faqs/:id} : delete the "id" faq.
     *
     * @param id the id of the faq to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/faqs/{id}")
    public ResponseEntity<Void> deleteFaq(@PathVariable Long id) {
        log.debug("REST request to delete Faq : {}", id);
        faqService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
