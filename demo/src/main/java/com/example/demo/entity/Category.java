package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_id",
            sequenceName = "category_id",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_id")
    private Integer id;
    private String name;

    public Category(Integer id) {
        this.id = id;
    }
}
