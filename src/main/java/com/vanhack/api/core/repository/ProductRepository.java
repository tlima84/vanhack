package com.vanhack.api.core.repository;

import com.vanhack.api.core.repository.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findBySlug(String slug);
}
