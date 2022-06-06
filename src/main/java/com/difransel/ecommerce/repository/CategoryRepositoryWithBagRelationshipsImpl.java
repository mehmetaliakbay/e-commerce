package com.difransel.ecommerce.repository;

import com.difransel.ecommerce.domain.Category;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class CategoryRepositoryWithBagRelationshipsImpl implements CategoryRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Category> fetchBagRelationships(Optional<Category> category) {
//        return category.map(this::fetchIds);
        return null;
    }

    @Override
    public Page<Category> fetchBagRelationships(Page<Category> categories) {
        return new PageImpl<>(fetchBagRelationships(categories.getContent()), categories.getPageable(), categories.getTotalElements());
    }

    @Override
    public List<Category> fetchBagRelationships(List<Category> categories) {
//        return Optional.of(categories).map(this::fetchIds).orElse(Collections.emptyList());
        return null;
    }

//    Category fetchIds(Category result) {
//        return entityManager
//            .createQuery("select category from Category category left join fetch category.ids where category is :category", Category.class)
//            .setParameter("category", result)
//            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
//            .getSingleResult();
//    }
//
//    List<Category> fetchIds(List<Category> categories) {
//        return entityManager
//            .createQuery(
//                "select distinct category from Category category left join fetch category.ids where category in :categories",
//                Category.class
//            )
//            .setParameter("categories", categories)
//            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
//            .getResultList();
//    }
}
