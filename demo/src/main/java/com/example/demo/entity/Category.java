package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
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
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> product;

    public Category(Integer id) {
        this.id = id;
    }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


}
