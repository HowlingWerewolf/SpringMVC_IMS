package com.ims.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.data.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
