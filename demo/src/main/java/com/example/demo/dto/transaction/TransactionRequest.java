package com.example.demo.dto.transaction;


import com.example.demo.entity.transaction.TransactionsDetail;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {


    private Integer id;
    private Integer totalAmount;
    private Integer totalPay;
    private List<TransactionDetailRequest> transactionsDetail;
    private LocalDate transactionDate = LocalDate.now();

//    public Stream<Integer> stream(List<Integer> list) {
//        return list == null || list.isEmpty() ? Stream.empty() : list.stream();
//    }

}
