package com.example.demo.entity.transaction;


import com.example.demo.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
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
    @NotNull(message = "totalamount cannot be null")
    private Integer totalAmount;
    @Column(name = "total_pay")
    @NotNull(message = "totalpay cannot be null")
    private Integer totalPay;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;
    @JsonIgnore
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionsDetail> transactionsDetails;

    public Transaction(Integer id) {
        this.id = id;
    }
}
