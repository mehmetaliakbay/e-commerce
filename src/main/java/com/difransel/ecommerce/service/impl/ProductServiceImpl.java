package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.model.Product;
import com.difransel.ecommerce.repository.ProductRepository;
import com.difransel.ecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        log.debug("Request to save Product : {}", product);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        log.debug("Request to save Product : {}", product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> partialUpdate(Product product) {
        log.debug("Request to partially update Product : {}", product);

        return productRepository
            .findById(product.getId())
            .map(existingProduct -> {
                if (product.getTitle() != null) {
                    existingProduct.setTitle(product.getTitle());
                }
                if (product.getKeywords() != null) {
                    existingProduct.setKeywords(product.getKeywords());
                }
                if (product.getDescription() != null) {
                    existingProduct.setDescription(product.getDescription());
                }
                if (product.getImage() != null) {
                    existingProduct.setImage(product.getImage());
                }
                if (product.getDetail() != null) {
                    existingProduct.setDetail(product.getDetail());
                }
                if (product.getPrice() != null) {
                    existingProduct.setPrice(product.getPrice());
                }
                if (product.getAmount() != null) {
                    existingProduct.setAmount(product.getAmount());
                }
                if (product.getSlug() != null) {
                    existingProduct.setSlug(product.getSlug());
                }
                if (product.getStatus() != null) {
                    existingProduct.setStatus(product.getStatus());
                }
                if (product.getCreatedAt() != null) {
                    existingProduct.setCreatedAt(product.getCreatedAt());
                }
                if (product.getUpdatedAt() != null) {
                    existingProduct.setUpdatedAt(product.getUpdatedAt());
                }

                return existingProduct;
            })
            .map(productRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable);
    }

    public Page<Product> findAllWithEagerRelationships(Pageable pageable) {
        return productRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }
}
