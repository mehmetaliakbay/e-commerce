package com.difransel.ecommerce.service;

import com.difransel.ecommerce.model.ProductUpdateRequest;

public interface ProductSaveService {

     void updateProduct(Long productId, ProductUpdateRequest request);

}
