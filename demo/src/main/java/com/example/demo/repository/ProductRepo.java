package com.example.demo.repository;

import com.example.demo.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE products SET sold = sold + 1 WHERE  id = ?1", nativeQuery = true)
//    void updateProduct(int id,);

    @Query("FROM Product WHERE category.id = ?1")
    List<Product> findByCategoryId(Integer id);

    @Query("FROM Product p WHERE p.title LIKE %:title%")
    List<Product> findByTitleLike(@Param("title") String title);
}
