package com.example.demo.entity;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.entity.transaction.TransactionsDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;


import java.util.List;

@Entity
@Data
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor @AllArgsConstructor
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence")
    private Integer id;
    @Column(nullable = false)
    @Size(max = 20)
    private String title;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    @Size(max = 255)
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<TransactionsDetail> transactionsDetails;

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String title, Integer price, String image, Category category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.category = category;
    }


}
