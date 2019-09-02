package com.sda.caloriescalculator.repository;

import com.sda.caloriescalculator.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(
            value = "SELECT * FROM product_entity WHERE name LIKE :product", nativeQuery = true)
    List<ProductEntity> findAllActiveUsersNative(@Param("product") String product);

//    ProductEntity findProductEntityByName(String name);
    ProductEntity findByName(String name);
}
