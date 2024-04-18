package com.example.demo.controller;


import com.example.demo.dto.CategoryRequest;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pos/api")
public class CategoryController {

    @Autowired
    CategoryService cs;

//    @GetMapping("/listproduct")
//    public ResponseEntity viewByCategoryId(@RequestParam int id){
//        return ResponseEntity.ok(cs.showCategoryId(id));
//    }

    @GetMapping("/listproduct/category")
    public ResponseEntity viewAllCategory(){
        return ResponseEntity.ok(cs.showAllCategory());
    }

    @PostMapping("/insert")
    public ResponseEntity insertCategory(@RequestBody CategoryRequest cr){
        return ResponseEntity.ok(cs.createCategory(cr));
    }

}
