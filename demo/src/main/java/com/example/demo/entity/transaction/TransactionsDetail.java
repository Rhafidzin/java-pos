package com.example.demo.entity.transaction;


import com.example.demo.dto.transaction.TransactionDetailRequest;
import com.example.demo.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.stream.Stream;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
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
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Integer subtotal;
    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


}
