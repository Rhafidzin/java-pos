package com.example.demo.repository.transaction;

import com.example.demo.entity.transaction.TransactionsDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepo
        extends JpaRepository<TransactionsDetail, Integer> {
}
