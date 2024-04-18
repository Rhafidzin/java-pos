package com.example.demo.dto.transaction;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class TransactionRequest {


    private Integer id;
    private Integer totalAmount;
    private Integer totalPay;
    private LocalDate transactionDate = LocalDate.now();
}
