package com.example.demo.controller.transaction;


import com.example.demo.dto.ResponseRequest;
import com.example.demo.dto.transaction.TransactionDetailRequest;
import com.example.demo.entity.transaction.TransactionsDetail;
import com.example.demo.service.transaction.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pos/api")
public class TransactionDetailController {

    @Autowired
    TransactionDetailService transactionDetailService;

    @GetMapping("/listtransaksidetail")
    public ResponseEntity<ResponseRequest> getAllTransactionDetail(){
        return transactionDetailService.readAllTransactionDetail();
    }

    @GetMapping("/listtransaksidetail/{id}")
    private ResponseEntity<ResponseRequest> getTransactionDetailId(@PathVariable("id") int id ){
        return transactionDetailService.findTransactionDetailId(id);
    }

    @PostMapping("/addtransaksidetail")
    public ResponseEntity<ResponseRequest> insertTransactionDetail(@RequestBody TransactionDetailRequest tdr){
        return transactionDetailService.createTransactionDetail(tdr);
    }
}
