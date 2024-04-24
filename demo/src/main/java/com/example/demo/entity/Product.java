package com.example.demo.entity;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.entity.transaction.TransactionsDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


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
    @NotNull(message = "title cannot be null")
    @NotBlank(message = "title cannot be blank")
    @Size(max = 20, message = "title maximum size is 20 character")
    private String title;
    @Min(1)
    @Max(2000000000)
    @NotNull(message = "Price cannot be null")
    private Integer price;
    @Size(max = 255, message = "image maximum size is 255 character")
    @NotNull(message = "image cannot be null")
    @NotBlank(message = "image cannot be blank")
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
