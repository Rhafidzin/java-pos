package com.example.demo.repository.transaction;


import com.example.demo.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo
        extends JpaRepository<Transaction, Integer> {
}
