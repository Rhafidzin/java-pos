package com.example.demo.service.transaction;

import com.example.demo.dto.ResponseRequest;
import com.example.demo.dto.transaction.TransactionDetailRequest;
import com.example.demo.dto.transaction.TransactionRequest;
import com.example.demo.entity.Product;
import com.example.demo.entity.transaction.Transaction;
import com.example.demo.entity.transaction.TransactionsDetail;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.transaction.TransactionDetailRepo;
import com.example.demo.repository.transaction.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class TransactionService {

    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    TransactionDetailRepo transactionDetailRepo;
    @Autowired
    ProductRepo productRepo;


    public ResponseRequest findAllTransaction() {
        try{
            List<?> result = transactionRepo.findAll();
            if(result.isEmpty()){
                return new ResponseRequest(500, "Tabel kosong", result);
        }
            return new ResponseRequest(200, "Data berhasil diread", result);

        } catch(Exception e){
            return new ResponseRequest(500, "Terjadi kesalahan");
        }

    }

    public ResponseRequest insertTransaction(TransactionRequest tr) {
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(tr.getTransactionDate());
        transaction.setTotalAmount(tr.getTotalAmount());
        transaction.setTotalPay(tr.getTotalPay());

        Object result = transactionRepo.save(transaction);
        return new ResponseRequest(200, "Data berhasil diinput", result);
    }

    public ResponseEntity<ResponseRequest> createTransaction(TransactionRequest treq) {
        for (var transactionDetailData : treq.getTransactionsDetail()) {
            var product = productRepo.findById(transactionDetailData.getProduct().getId()).orElse(null);
            // cek entity product
            if (product == null) {
                return new ResponseEntity<>(new ResponseRequest(
                        HttpStatus.NOT_FOUND.value(),
                        "Product ID tidak ditemukan"),
                        HttpStatus.NOT_FOUND);
            }
            // cek expected sub total tiap entity transaction detail
            Integer expectedSubTotal = transactionDetailData.getQuantity() * product.getPrice();
            System.out.println("EXPECT sub total = " + expectedSubTotal);
            if (!expectedSubTotal.equals(transactionDetailData.getSubtotal())) {
                return new ResponseEntity<>(new ResponseRequest(
                        HttpStatus.BAD_REQUEST.value(),
                        "Subtotal tidak sesuai (product "+ product.getId()+")" ),
                        HttpStatus.BAD_REQUEST
                );
            }
        }
        if (treq.getTotalAmount() > treq.getTotalPay()) {
            return new ResponseEntity<>(new ResponseRequest(
                    HttpStatus.BAD_REQUEST.value(),
                    "Total Amount harus lebih dari Total pay"),
                    HttpStatus.BAD_REQUEST);
        }
        Integer totalAmountDetails = treq.getTransactionsDetail().stream()
                .mapToInt(TransactionDetailRequest::getSubtotal)
                .sum();
        System.out.println("total amount :" + totalAmountDetails);
        if (!totalAmountDetails.equals(treq.getTotalAmount())) {
            return new ResponseEntity<>(
                    new ResponseRequest(
                            HttpStatus.BAD_REQUEST.value(),
                            "Total amount tidak sesuai"),
                    HttpStatus.BAD_REQUEST
            );
        }

        System.out.println(treq.getTransactionsDetail());
        try{
            Transaction transaction = new Transaction();
            transaction.setTotalAmount(treq.getTotalAmount());
            transaction.setTotalPay(treq.getTotalPay());
            transaction.setTransactionDate(treq.getTransactionDate());
            transactionRepo.save(transaction);
            System.out.println(transaction);
            for(var transactionDetailRequest : treq.getTransactionsDetail()){
                TransactionsDetail transactionsDetail = new TransactionsDetail();
                transactionsDetail.setTransaction(transaction);
                transactionsDetail.setProduct(transactionDetailRequest.getProduct());
                transactionsDetail.setSubtotal(transactionDetailRequest.getSubtotal());
                transactionsDetail.setQuantity(transactionDetailRequest.getQuantity());
                transactionDetailRepo.save(transactionsDetail);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<>(
                    new ResponseRequest(
                            HttpStatus.BAD_REQUEST.value(),
                            "Terjadi kesalahan"),
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>
                (new ResponseRequest(HttpStatus.CREATED.value(),
                        "Berhasil")
                        , HttpStatus.CREATED);
    }

//        @Transactional
//    public ResponseEntity<ResponseRequest> createTransaction(TransactionRequest treq) {
//        if(treq.getTotalAmount() > treq.getTotalPay()){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        var tdr = new TransactionDetailRequest();
//        Integer totalAmountFromDetails = treq.getTransactionsDetail().stream()
//                .mapToInt(tdr::getSubtotal)
//                .sum();
//        System.out.println(tdr);
//        System.out.println(totalAmountFromDetails);
//
//        try{
//            Transaction transaction = new Transaction();
//            transaction.setTotalPay(treq.getTotalPay());
//            transaction.setTotalAmount(treq.getTotalAmount());
//            System.out.println(transaction);
//            for(TransactionDetailRequest detailRequest : treq.getTransactionsDetail()){
//
//                Product product = productRepo.findById(detailRequest.getProduct());
//                System.out.println(product);
//            }
//
////            transactionRepo.save(transaction);
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>
//                (new ResponseRequest(HttpStatus.CREATED.value(), "Berhasil")
//                        , HttpStatus.CREATED);
//    }


}
