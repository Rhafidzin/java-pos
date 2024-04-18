package com.example.demo.service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ResponseRequest;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseRequest insert(ProductRequest pr) {
            try {
                /* Periksa nilai dari category_id telah mempunyai value atau belum */
                System.out.println(pr.getCategory().getId());
                Boolean condition = categoryRepo.findById(pr.getCategory().getId()).isEmpty();
                if(condition){
                    throw new NullPointerException();
                }
                Product product = new Product();
                product.setTitle(pr.getTitle());
                product.setPrice(pr.getPrice());
                product.setImage(pr.getImage());
                product.setCategory(pr.getCategory());
                Object result = productRepo.save(product);
                return new ResponseRequest(HttpStatus.CREATED.value(), "Data berhasil dibuat", result);
            } catch (NullPointerException e){
                return new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Category_id tidak ditemukan");
            }
    }

    public ResponseRequest readAllProduct(Integer id, String sortDirection, String sortBy){
        try{
            /* Cek value parameter category_id */
            if(id == null){
                /* Jika tidak input parameter, output semua data products */
                Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
//                Pageable page = PageRequest.of(sort);
                Object result = productRepo.findAll(sort);
                return new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result);
            } else {
                /* kondisi ketika parameter category_id !null */
                List<Product> result = productRepo.findByCategoryId(id);
                if(result.isEmpty()){
                    return new ResponseRequest(HttpStatus.NOT_FOUND.value(), "Category ID tidak ditemukan");
                }
                return new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result);
            }
        } catch (Exception e){
            return new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Data gagal diread");
        }

    }

    public ResponseRequest readLikeProduct(String title, String sortDirection, String sortBy) {
        try{
            /* Cek value parameter LIKE title */
            if(title == null){
                /* Jika tidak input parameter, output semua data products */
                Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
//                Pageable page = PageRequest.of(sort);
                Object result = productRepo.findAll(sort);
                return new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result);
            } else {
                /* kondisi ketika parameter title !null */
                List<Product> result = productRepo.findByTitleLike(title);
                if(result.isEmpty()){
                    return new ResponseRequest(HttpStatus.NOT_FOUND.value(), "Title tidak ditemukan");
                }
                return new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result);
            }

        } catch (Exception e){
            return new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Data gagal diread");
        }
    }

    public ResponseRequest deleteProductId(int id) {
        Optional<Product> condition = productRepo.findById(id);
        if(condition.isEmpty()){
            return new ResponseRequest(HttpStatus.NOT_FOUND.value(), "ID tidak ditemukan");
        } else {
            productRepo.deleteById(id);
            return new ResponseRequest(HttpStatus.OK.value(), HttpStatus.OK, "Data berhasil dihapus", condition);
        }

    }

    public ResponseRequest getProductId(int id) {
        Optional<Product> result = productRepo.findById(id);
        if (result.isEmpty()){
            return new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "ID tidak ditemukan");
        } else {

            return new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result);
        }
    }

    public ResponseRequest updateProduct(int id, ProductRequest pr) {
        try{
            Product result =  productRepo.getReferenceById(id);
            result.setTitle(pr.getTitle());
            result.setImage(pr.getImage());
            result.setPrice(pr.getPrice());
            result.setCategory(pr.getCategory());
            return new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diupdate", result);
        } catch (Exception e){
            return new ResponseRequest(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "Gagal akses");
        }
    }

    public ResponseRequest deleteAllProduct() {
        productRepo.deleteAll();
        return new ResponseRequest(HttpStatus.OK.value(), "Data berhasil dihapus");
    }


}
