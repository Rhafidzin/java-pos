package com.example.demo.service.transaction;

import com.example.demo.dto.ResponseRequest;
import com.example.demo.dto.transaction.TransactionDetailRequest;
import com.example.demo.entity.Product;
import com.example.demo.entity.transaction.Transaction;
import com.example.demo.entity.transaction.TransactionsDetail;
import com.example.demo.repository.transaction.TransactionDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TransactionDetailService {

    @Autowired
    TransactionDetailRepo transactionDetailRepo;

    public ResponseRequest readAllTransactionDetail() {
        try{
            Object result = transactionDetailRepo.findAll();
            if(((List<?>) result).isEmpty()){
                return new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Tabel kosong", result);
            }
            return new ResponseRequest(HttpStatus.ACCEPTED.value(), "Data berhasil diread", result);

        } catch(Exception e){
            return new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Terjadi Kesalahan");
        }

    }

    public ResponseRequest createTransactionDetail(TransactionDetailRequest tdr) {
        TransactionsDetail transactionsDetail = new TransactionsDetail();
        transactionsDetail.setQuantity(tdr.getQuantity());
        transactionsDetail.setProduct(tdr.getProduct());
        transactionsDetail.setSubtotal(tdr.getSubtotal());
        transactionsDetail.setTransaction(tdr.getTransaction());
        Object result = transactionDetailRepo.save(transactionsDetail);
        return new ResponseRequest(HttpStatus.CREATED.value(), "Data berhasil diinput", result);
    }

    public ResponseRequest findTransactionDetailId(int id) {
        Optional<TransactionsDetail> result = transactionDetailRepo.findById(id);

        if (result.isEmpty()){
            return new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "ID tidak ditemukan");
        } else {

            return new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result);
        }
    }

}
