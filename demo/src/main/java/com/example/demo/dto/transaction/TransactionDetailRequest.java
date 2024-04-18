package com.example.demo.dto.transaction;

import com.example.demo.entity.Product;
import com.example.demo.entity.transaction.Transaction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
public class TransactionDetailRequest {

    private Integer id;
    private Product product;
    private Integer quantity;
    private Integer subtotal;
    private Transaction transaction;
}
