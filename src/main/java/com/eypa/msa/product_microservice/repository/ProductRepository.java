package com.eypa.msa.product_microservice.repository;

import com.eypa.msa.product_microservice.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<ProductEntity, String>
{
    Optional<ProductEntity> findById(String id);
}
