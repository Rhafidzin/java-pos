package com.example.demo.controller.transaction;


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
    private ResponseEntity getAllTransaction(){
        return ResponseEntity.ok(transactionService.findAllTransaction());
    }

    @PostMapping("/addtransaction")
    private ResponseEntity insertTransaction(@RequestBody TransactionRequest tr){
        return ResponseEntity.ok(transactionService.createTransaction(tr));
    }
}
