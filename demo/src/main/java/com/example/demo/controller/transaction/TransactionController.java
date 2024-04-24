package com.example.demo.controller.transaction;


import com.example.demo.dto.ResponseRequest;
import com.example.demo.dto.transaction.TransactionRequest;
import com.example.demo.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pos/api")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/listtransaksi")
    private ResponseEntity<ResponseRequest> getAllTransaction(){
        return transactionService.findAllTransaction();
    }

    @PostMapping("/addtransaction1")
    private ResponseEntity<ResponseRequest> insertTransaction(@RequestBody TransactionRequest tr){
        return transactionService.insertTransaction(tr);
    }

    @PostMapping("/addtransaction")
    private ResponseEntity<ResponseRequest> createTransaction(@RequestBody TransactionRequest tr){
        return transactionService.createTransaction(tr);
    }
}
