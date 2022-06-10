package com.difransel.ecommerce.repository;

import com.difransel.ecommerce.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OrderProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {}
