package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;
    private Integer price;
    private String image;

    public Product(Integer id) {
        this.id = id;
    }

}
