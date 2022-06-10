package com.difransel.ecommerce.repository;

import com.difransel.ecommerce.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryWithBagRelationships {
    Optional<Product> fetchBagRelationships(Optional<Product> product);

    List<Product> fetchBagRelationships(List<Product> products);

    Page<Product> fetchBagRelationships(Page<Product> products);
}
