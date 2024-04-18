package com.example.demo.entity.transaction;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @SequenceGenerator(
            name = "transaction_id",
            sequenceName = "transaction_id",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_id")
    private Integer id;
    @Column(name = "total_amount")
    private Integer totalAmount;
    @Column(name = "total_pay")
    private Integer totalPay;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    public Transaction(Integer id) {
        this.id = id;
    }
}
