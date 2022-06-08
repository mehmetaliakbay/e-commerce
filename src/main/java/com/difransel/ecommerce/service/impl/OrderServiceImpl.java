package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.domain.CustomerOrder;
import com.difransel.ecommerce.repository.CustomerOrderRepository;
import com.difransel.ecommerce.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CustomerOrder}.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final CustomerOrderRepository orderRepository;

    public OrderServiceImpl(CustomerOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public CustomerOrder save(CustomerOrder customerOrder) {
        log.debug("Request to save Order : {}", customerOrder);
        return orderRepository.save(customerOrder);
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        log.debug("Request to save Order : {}", customerOrder);
        return orderRepository.save(customerOrder);
    }

    @Override
    public Optional<CustomerOrder> partialUpdate(CustomerOrder customerOrder) {
        log.debug("Request to partially update Order : {}", customerOrder);

        return orderRepository
            .findById(customerOrder.getId())
            .map(existingOrder -> {
                if (customerOrder.getName() != null) {
                    existingOrder.setName(customerOrder.getName());
                }
                if (customerOrder.getSurname() != null) {
                    existingOrder.setSurname(customerOrder.getSurname());
                }
                if (customerOrder.getEmail() != null) {
                    existingOrder.setEmail(customerOrder.getEmail());
                }
                if (customerOrder.getAddress() != null) {
                    existingOrder.setAddress(customerOrder.getAddress());
                }
                if (customerOrder.getPhone() != null) {
                    existingOrder.setPhone(customerOrder.getPhone());
                }
                if (customerOrder.getTotal() != null) {
                    existingOrder.setTotal(customerOrder.getTotal());
                }
                if (customerOrder.getStatus() != null) {
                    existingOrder.setStatus(customerOrder.getStatus());
                }
                if (customerOrder.getNote() != null) {
                    existingOrder.setNote(customerOrder.getNote());
                }
                if (customerOrder.getIp() != null) {
                    existingOrder.setIp(customerOrder.getIp());
                }
                if (customerOrder.getCreatedAt() != null) {
                    existingOrder.setCreatedAt(customerOrder.getCreatedAt());
                }
                if (customerOrder.getUpdatedAt() != null) {
                    existingOrder.setUpdatedAt(customerOrder.getUpdatedAt());
                }

                return existingOrder;
            })
            .map(orderRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerOrder> findAll() {
        log.debug("Request to get all Orders");
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerOrder> findOne(Long id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        orderRepository.deleteById(id);
    }
}
