package com.techie.springbootrediscache.repository;

import com.techie.springbootrediscache.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
