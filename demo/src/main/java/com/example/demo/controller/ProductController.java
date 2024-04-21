package com.example.demo.controller;


import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ResponseRequest;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pos/api")
public class ProductController {

    @Autowired
    private ProductService ps;
    @PostMapping("/addproduct")
    public ResponseEntity<ResponseRequest> insertProduct(@RequestBody ProductRequest pr){
        return ps.insert(pr);
    }

    @GetMapping("/listproduct")
    public ResponseEntity<ResponseRequest> getProduct(
            @RequestParam(required = false) Integer category_id,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "id") String sort_by,
            @RequestParam(defaultValue = "DESC") String sort_order
    ){
        if (title == null){

            return ps.readAllProduct(
                    category_id,
                    sort_order,
                    sort_by
            );
        } else {
            return ps.readLikeProduct(
                    title,
                    sort_order,
                    sort_by
            );
        }
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<ResponseRequest> deleteById(@PathVariable("id") int id){
        return ps.deleteProductId(id);
    }

    @GetMapping("/detailproduct/{id}")
    public ResponseEntity<ResponseRequest> getById(@PathVariable("id") int id){
        return ps.getProductId(id);
    }

    @PutMapping("/updateproduct/{id}")
    public ResponseEntity<ResponseRequest> updateProduct(@PathVariable("id") int id, @RequestBody ProductRequest pr){
        return ps.updateProduct(id, pr);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<ResponseRequest> deleteAll(){
        return ResponseEntity.ok(ps.deleteAllProduct());
    }


}
