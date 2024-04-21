package com.example.demo.repository.transaction;

import com.example.demo.entity.transaction.TransactionsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionDetailRepo
        extends JpaRepository<TransactionsDetail, Integer> {

}
