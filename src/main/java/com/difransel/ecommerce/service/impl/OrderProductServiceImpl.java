package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.model.OrderProduct;
import com.difransel.ecommerce.repository.OrderProductRepository;
import com.difransel.ecommerce.service.OrderProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderProduct}.
 */
@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    private final Logger log = LoggerFactory.getLogger(OrderProductServiceImpl.class);

    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        log.debug("Request to save OrderProduct : {}", orderProduct);
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public OrderProduct update(OrderProduct orderProduct) {
        log.debug("Request to save OrderProduct : {}", orderProduct);
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public Optional<OrderProduct> partialUpdate(OrderProduct orderProduct) {
        log.debug("Request to partially update OrderProduct : {}", orderProduct);

        return orderProductRepository
            .findById(orderProduct.getId())
            .map(existingOrderProduct -> {
                if (orderProduct.getPrice() != null) {
                    existingOrderProduct.setPrice(orderProduct.getPrice());
                }
                if (orderProduct.getAmount() != null) {
                    existingOrderProduct.setAmount(orderProduct.getAmount());
                }
                if (orderProduct.getTotal() != null) {
                    existingOrderProduct.setTotal(orderProduct.getTotal());
                }
                if (orderProduct.getIp() != null) {
                    existingOrderProduct.setIp(orderProduct.getIp());
                }
                if (orderProduct.getNote() != null) {
                    existingOrderProduct.setNote(orderProduct.getNote());
                }
                if (orderProduct.getStatus() != null) {
                    existingOrderProduct.setStatus(orderProduct.getStatus());
                }
                if (orderProduct.getCreatedAt() != null) {
                    existingOrderProduct.setCreatedAt(orderProduct.getCreatedAt());
                }
                if (orderProduct.getUpdatedAt() != null) {
                    existingOrderProduct.setUpdatedAt(orderProduct.getUpdatedAt());
                }

                return existingOrderProduct;
            })
            .map(orderProductRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderProduct> findAll() {
        log.debug("Request to get all OrderProducts");
        return orderProductRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderProduct> findOne(Long id) {
        log.debug("Request to get OrderProduct : {}", id);
        return orderProductRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderProduct : {}", id);
        orderProductRepository.deleteById(id);
    }
}
