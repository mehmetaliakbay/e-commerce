package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.model.Product;
import com.difransel.ecommerce.model.ProductUpdateRequest;
import com.difransel.ecommerce.service.ProductReadService;
import com.difransel.ecommerce.service.ProductSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ProductSaveServiceImpl implements ProductSaveService {

    private final ProductReadService productReadService;

    @Override
    @Transactional
    public void updateProduct(Long productId, ProductUpdateRequest request) {
        final Product product = productReadService.findById(productId);

    }
}
