package com.example.demo.service;


import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.ResponseRequest;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo cr;

    public ResponseEntity<ResponseRequest> showAllCategory(){
        Object result = cr.findAll();
        return new ResponseEntity<>(
                new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result),
                HttpStatus.OK
        );
    }

    public ResponseEntity<ResponseRequest> createCategory(@NotNull CategoryRequest catReq) {
        Category cat = new Category();
        cat.setName(catReq.getName());
        Object result = cr.save(cat);
        return new ResponseEntity<>(
                new ResponseRequest(HttpStatus.OK.value(), "Data berhasil dibuat", result),
                HttpStatus.OK
        );
    }

    public Object showCategoryId(int id) {
        Optional<Category> result = cr.findById(id);
        System.out.println(result);
        if (result.isEmpty()){
            return new ResponseRequest(500, "ID tidak ditemukan");
        } else {
            return new ResponseRequest(200, "Data berhasil diread", result);
        }
    }
}
