package com.difransel.ecommerce.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message+" id'li ürün bulunamadı.");
    }
}
