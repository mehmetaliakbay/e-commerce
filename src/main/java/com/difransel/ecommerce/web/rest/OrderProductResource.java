package com.difransel.ecommerce.web.rest;

import com.difransel.ecommerce.domain.OrderProduct;
import com.difransel.ecommerce.repository.OrderProductRepository;
import com.difransel.ecommerce.service.OrderProductService;
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
 * REST controller for managing {@link OrderProduct}.
 */
@RestController
@RequestMapping("/api")
public class OrderProductResource {

    private final Logger log = LoggerFactory.getLogger(OrderProductResource.class);

    private static final String ENTITY_NAME = "orderProduct";

//    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderProductService orderProductService;

    private final OrderProductRepository orderProductRepository;

    public OrderProductResource(OrderProductService orderProductService, OrderProductRepository orderProductRepository) {
        this.orderProductService = orderProductService;
        this.orderProductRepository = orderProductRepository;
    }

    /**
     * {@code POST  /order-products} : Create a new orderProduct.
     *
     * @param orderProduct the orderProduct to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderProduct, or with status {@code 400 (Bad Request)} if the orderProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-products")
    public ResponseEntity<OrderProduct> createOrderProduct(@RequestBody OrderProduct orderProduct) throws URISyntaxException {
        log.debug("REST request to save OrderProduct : {}", orderProduct);
        if (orderProduct.getId() != null) {
            throw new BadRequestAlertException("A new orderProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderProduct result = orderProductService.save(orderProduct);
        return ResponseEntity
            .created(new URI("/api/order-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-products/:id} : Updates an existing orderProduct.
     *
     * @param id the id of the orderProduct to save.
     * @param orderProduct the orderProduct to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderProduct,
     * or with status {@code 400 (Bad Request)} if the orderProduct is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderProduct couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-products/{id}")
    public ResponseEntity<OrderProduct> updateOrderProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderProduct orderProduct
    ) throws URISyntaxException {
        log.debug("REST request to update OrderProduct : {}, {}", id, orderProduct);
        if (orderProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderProduct.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderProductRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OrderProduct result = orderProductService.update(orderProduct);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderProduct.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /order-products/:id} : Partial updates given fields of an existing orderProduct, field will ignore if it is null
     *
     * @param id the id of the orderProduct to save.
     * @param orderProduct the orderProduct to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderProduct,
     * or with status {@code 400 (Bad Request)} if the orderProduct is not valid,
     * or with status {@code 404 (Not Found)} if the orderProduct is not found,
     * or with status {@code 500 (Internal Server Error)} if the orderProduct couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/order-products/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OrderProduct> partialUpdateOrderProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderProduct orderProduct
    ) throws URISyntaxException {
        log.debug("REST request to partial update OrderProduct partially : {}, {}", id, orderProduct);
        if (orderProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderProduct.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderProductRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrderProduct> result = orderProductService.partialUpdate(orderProduct);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderProduct.getId().toString())
        );
    }

    /**
     * {@code GET  /order-products} : get all the orderProducts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderProducts in body.
     */
    @GetMapping("/order-products")
    public List<OrderProduct> getAllOrderProducts() {
        log.debug("REST request to get all OrderProducts");
        return orderProductService.findAll();
    }

    /**
     * {@code GET  /order-products/:id} : get the "id" orderProduct.
     *
     * @param id the id of the orderProduct to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderProduct, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-products/{id}")
    public ResponseEntity<OrderProduct> getOrderProduct(@PathVariable Long id) {
        log.debug("REST request to get OrderProduct : {}", id);
        Optional<OrderProduct> orderProduct = orderProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderProduct);
    }

    /**
     * {@code DELETE  /order-products/:id} : delete the "id" orderProduct.
     *
     * @param id the id of the orderProduct to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-products/{id}")
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable Long id) {
        log.debug("REST request to delete OrderProduct : {}", id);
        orderProductService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
