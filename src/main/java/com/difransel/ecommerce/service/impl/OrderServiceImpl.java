package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.domain.Order;
import com.difransel.ecommerce.repository.OrderRepository;
import com.difransel.ecommerce.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Order}.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        log.debug("Request to save Order : {}", order);
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        log.debug("Request to save Order : {}", order);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> partialUpdate(Order order) {
        log.debug("Request to partially update Order : {}", order);

        return orderRepository
            .findById(order.getId())
            .map(existingOrder -> {
                if (order.getName() != null) {
                    existingOrder.setName(order.getName());
                }
                if (order.getSurname() != null) {
                    existingOrder.setSurname(order.getSurname());
                }
                if (order.getEmail() != null) {
                    existingOrder.setEmail(order.getEmail());
                }
                if (order.getAddress() != null) {
                    existingOrder.setAddress(order.getAddress());
                }
                if (order.getPhone() != null) {
                    existingOrder.setPhone(order.getPhone());
                }
                if (order.getTotal() != null) {
                    existingOrder.setTotal(order.getTotal());
                }
                if (order.getStatus() != null) {
                    existingOrder.setStatus(order.getStatus());
                }
                if (order.getNote() != null) {
                    existingOrder.setNote(order.getNote());
                }
                if (order.getIp() != null) {
                    existingOrder.setIp(order.getIp());
                }
                if (order.getCreatedAt() != null) {
                    existingOrder.setCreatedAt(order.getCreatedAt());
                }
                if (order.getUpdatedAt() != null) {
                    existingOrder.setUpdatedAt(order.getUpdatedAt());
                }

                return existingOrder;
            })
            .map(orderRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        log.debug("Request to get all Orders");
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findOne(Long id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        orderRepository.deleteById(id);
    }
}
