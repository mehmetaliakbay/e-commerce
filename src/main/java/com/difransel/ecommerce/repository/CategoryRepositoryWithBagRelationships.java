package com.difransel.ecommerce.repository;

import com.difransel.ecommerce.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryWithBagRelationships {
    Optional<Category> fetchBagRelationships(Optional<Category> category);

    List<Category> fetchBagRelationships(List<Category> categories);

    Page<Category> fetchBagRelationships(Page<Category> categories);
}
