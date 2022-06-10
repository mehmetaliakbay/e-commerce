package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.exception.ProductNotFoundException;
import com.difransel.ecommerce.model.Product;
import com.difransel.ecommerce.repository.ProductRepository;
import com.difransel.ecommerce.service.ProductReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductReadServiceImpl implements ProductReadService {
    private final ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }


}
