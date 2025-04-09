package com.ims.repository.model.access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.repository.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
