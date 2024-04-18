package com.example.demo.service.transaction;

import com.example.demo.dto.ResponseRequest;
import com.example.demo.dto.transaction.TransactionRequest;
import com.example.demo.entity.transaction.Transaction;
import com.example.demo.repository.transaction.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    @Autowired
    TransactionRepo transactionRepo;


    public ResponseRequest findAllTransaction() {
        try{
            Object result = transactionRepo.findAll();
            if(((List<?>) result).isEmpty()){
                return new ResponseRequest(500, "Tabel kosong", result);
        }
            return new ResponseRequest(200, "Data berhasil diread", result);

        } catch(Exception e){
            return new ResponseRequest(500, "Terjadi kesalahan");
        }

    }

    public ResponseRequest createTransaction(TransactionRequest tr) {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(tr.getTransactionDate());
        transaction.setTotalAmount(tr.getTotalAmount());
        transaction.setTotalPay(tr.getTotalPay());

        Object result = transactionRepo.save(transaction);
        return new ResponseRequest(200, "Data berhasil diinput", result);
    }
}
