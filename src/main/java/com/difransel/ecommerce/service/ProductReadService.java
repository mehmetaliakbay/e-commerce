package com.difransel.ecommerce.service;

import com.difransel.ecommerce.model.Product;

public interface ProductReadService {
     Product findById(Long id);
}
