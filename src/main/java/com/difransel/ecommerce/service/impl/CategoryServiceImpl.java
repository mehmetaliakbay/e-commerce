package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.domain.Category;
import com.difransel.ecommerce.repository.CategoryRepository;
import com.difransel.ecommerce.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Category}.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        log.debug("Request to save Category : {}", category);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        log.debug("Request to save Category : {}", category);
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> partialUpdate(Category category) {
        log.debug("Request to partially update Category : {}", category);

        return categoryRepository
            .findById(category.getId())
            .map(existingCategory -> {
                if (category.getParentId() != null) {
                    existingCategory.setParentId(category.getParentId());
                }
                if (category.getTitle() != null) {
                    existingCategory.setTitle(category.getTitle());
                }
                if (category.getKeywords() != null) {
                    existingCategory.setKeywords(category.getKeywords());
                }
                if (category.getDescription() != null) {
                    existingCategory.setDescription(category.getDescription());
                }
                if (category.getImage() != null) {
                    existingCategory.setImage(category.getImage());
                }
                if (category.getStatus() != null) {
                    existingCategory.setStatus(category.getStatus());
                }
                if (category.getSlug() != null) {
                    existingCategory.setSlug(category.getSlug());
                }
                if (category.getCreatedAt() != null) {
                    existingCategory.setCreatedAt(category.getCreatedAt());
                }
                if (category.getUpdatedAt() != null) {
                    existingCategory.setUpdatedAt(category.getUpdatedAt());
                }

                return existingCategory;
            })
            .map(categoryRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Category> findAll(Pageable pageable) {
        log.debug("Request to get all Categories");
        return categoryRepository.findAll(pageable);
    }

    public Page<Category> findAllWithEagerRelationships(Pageable pageable) {
        return categoryRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        return categoryRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.deleteById(id);
    }
}
