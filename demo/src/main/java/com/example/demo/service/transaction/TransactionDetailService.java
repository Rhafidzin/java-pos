package com.example.demo.service.transaction;

import com.example.demo.dto.ResponseRequest;
import com.example.demo.dto.transaction.TransactionDetailRequest;
import com.example.demo.entity.Product;
import com.example.demo.entity.transaction.Transaction;
import com.example.demo.entity.transaction.TransactionsDetail;
import com.example.demo.repository.transaction.TransactionDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TransactionDetailService {

    @Autowired
    TransactionDetailRepo transactionDetailRepo;

    public ResponseEntity<ResponseRequest> readAllTransactionDetail() {
        try{
            List<TransactionsDetail> result = transactionDetailRepo.findAll();
            if(result.isEmpty()){
                return new ResponseEntity<>(
                    new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Tabel kosong", result),
                        HttpStatus.BAD_REQUEST
                );

            }
            return new ResponseEntity<>(
                    new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result),
                    HttpStatus.OK
            );

        } catch(Exception e){
            return new ResponseEntity<>(
                    new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Terjadi Kesalahan"),
                    HttpStatus.BAD_REQUEST
            );
        }

    }

    public ResponseEntity<ResponseRequest> createTransactionDetail(TransactionDetailRequest tdr) {
        TransactionsDetail transactionsDetail = new TransactionsDetail();
        transactionsDetail.setQuantity(tdr.getQuantity());
        transactionsDetail.setProduct(tdr.getProduct());
        transactionsDetail.setSubtotal(tdr.getSubtotal());
        transactionsDetail.setTransaction(tdr.getTransaction());
        Object result = transactionDetailRepo.save(transactionsDetail);
        return new ResponseEntity<>(
                new ResponseRequest(HttpStatus.CREATED.value(), "Data berhasil diinput", result),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<ResponseRequest> findTransactionDetailId(int id) {
        try{
            Optional<TransactionsDetail> result = transactionDetailRepo.findById(id);
            if (result.isEmpty()){
                return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.NOT_FOUND.value(), "ID tidak ditemukan"),
                        HttpStatus.NOT_FOUND
                );
            } else {
                return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result),
                        HttpStatus.OK
                );
            }
        } catch (Exception e){
            return new ResponseEntity<>(
                    new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Terjadi kesalahan"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
