package com.example.demo.dto.transaction;

import com.example.demo.entity.Product;
import com.example.demo.entity.transaction.Transaction;
import com.example.demo.entity.transaction.TransactionsDetail;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;


@Data
@Getter
public class TransactionDetailRequest {

    private Integer id;
    private Product product;
//    private Integer productId;
    private Integer quantity;
    private Integer subtotal;
    private Transaction transaction;

    public int getSubtotal(TransactionsDetail transactionsDetail) {
        return this.subtotal;
    }
}
