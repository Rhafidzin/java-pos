package com.example.demo.entity.transaction;


import com.example.demo.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transaction_details")
public class TransactionsDetail {

    @Id
    @SequenceGenerator(
            name = "transactiondetails_id",
            sequenceName = "transactiondetails_id",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transactiondetails_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    private Integer subtotal;
}
