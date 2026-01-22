package com.assigment_2_thuongnt87.repository;


import java.util.UUID;

import com.assigment_2_thuongnt87.entities.catalog.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
}
