package com.example.demo.service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ResponseRequest;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseEntity<ResponseRequest> insert(ProductRequest pr) {
            try {
                /* Periksa nilai dari category_id telah mempunyai value atau belum */
                    if(pr.getTitle().isEmpty() || pr.getTitle().isBlank() ||
                            pr.getPrice() == null || pr.getImage().isBlank() ||
                            pr.getImage().isEmpty()){
                        return new ResponseEntity<>(
                                new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Terdapat kolom kosong"),
                                HttpStatus.BAD_REQUEST
                        );
                    } else if(categoryRepo.findById(pr.getCategory().getId()).isEmpty()){
                        return new ResponseEntity<>(
                                new ResponseRequest(HttpStatus.NOT_FOUND.value(), "Category ID tidak ditemukan"),
                                HttpStatus.NOT_FOUND
                        );
                }

                Product product = new Product();
                product.setTitle(pr.getTitle());
                product.setPrice(pr.getPrice());
                product.setImage(pr.getImage());
                product.setCategory(pr.getCategory());
                Object result = productRepo.save(product);
                return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.CREATED.value(), "Data berhasil dibuat", result),
                        HttpStatus.CREATED
                );
            }
            catch (Exception e){
                return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Terjadi Kesalahan"),
                        HttpStatus.BAD_REQUEST
                );
            }
    }

    public ResponseEntity<ResponseRequest> readAllProduct(Integer id, String sortDirection, String sortBy){
        try{
            /* Cek value parameter category_id */
            if(id == null){
                /* Jika tidak input parameter, output semua data products */
                Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
//                Pageable page = PageRequest.of(sort);
                List<Product> result = productRepo.findAll(sort);
                return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result),
                        HttpStatus.OK
                );
            } else {
                /* kondisi ketika parameter category_id !null */
                List<Product> result = productRepo.findByCategoryId(id);
                if(result.isEmpty()){
                    return new ResponseEntity<>(

                    new ResponseRequest(HttpStatus.NOT_FOUND.value(), "Category ID tidak ditemukan"),
                            HttpStatus.NOT_FOUND
                    );
                }
                return new ResponseEntity<>(

                    new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result),
                        HttpStatus.OK
                );
            }
        } catch (Exception e){
            return new ResponseEntity<>(
                    new ResponseRequest(HttpStatus.BAD_REQUEST.value(), "Data gagal diread"),
                    HttpStatus.BAD_REQUEST
            );
        }

    }

    public ResponseEntity<ResponseRequest> readLikeProduct(String title, String sortDirection, String sortBy) {
        try{
            /* Cek value parameter LIKE title */
            if(title == null || title.isEmpty() || title.isBlank()){
                /* Jika tidak input parameter, output semua data products */
                Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
//                Pageable page = PageRequest.of(sort);
                Object result = productRepo.findAll(sort);
                return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result),
                        HttpStatus.OK
                );
            } else {
                /* kondisi ketika parameter title !null */
                List<Product> result = productRepo.findByTitleLike(title);
                if(result.isEmpty()){
                    return new ResponseEntity<>(
                            new ResponseRequest(HttpStatus.NOT_FOUND.value(), "Title tidak ditemukan"),
                            HttpStatus.NOT_FOUND
                    );
                }
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

    public ResponseEntity<ResponseRequest> deleteProductId(int id) {
        try{
            Optional<Product> condition = productRepo.findById(id);
            if(condition.isEmpty()){
                return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.NOT_FOUND.value(),
                                "Product ID tidak ditemukan"),
                        HttpStatus.NOT_FOUND
                );
            } else {
                productRepo.deleteById(id);
                return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.OK.value(),
                                "Data berhasil dihapus"),
                        HttpStatus.OK
                        );
            }
        } catch (Exception e){
            return new ResponseEntity<>(
                    new ResponseRequest(HttpStatus.BAD_REQUEST.value(),
                            "Terjadi kesalahan"),
                    HttpStatus.BAD_REQUEST
            );
        }

    }

    public ResponseEntity getProductId(int id) {
        try {

            Optional<Product> result = productRepo.findById(id);
            if (result.isEmpty()){
                return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.BAD_REQUEST.value(),
                                "ID tidak ditemukan"),
                                HttpStatus.BAD_REQUEST
                        );
            } else {
                return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.OK.value(), "Data berhasil diread", result),
                        HttpStatus.OK
                );
            }
        } catch (Exception e){
            return new ResponseEntity<>(
                    new ResponseRequest(HttpStatus.BAD_REQUEST.value(),
                            "Terjadi kesalahan"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    public ResponseEntity<ResponseRequest> updateProduct(int id, ProductRequest pr) {
        try{
            var product = productRepo.findById(id).orElse(null);
                if(product == null){
                    return new ResponseEntity<>(
                            new ResponseRequest(HttpStatus.NOT_FOUND.value(), "Product ID tidak ditemukan"),
                            HttpStatus.NOT_FOUND

                    );
                }
            var catId = categoryRepo.findById(pr.getCategory().getId()).isEmpty();
                if (catId){
                    return new ResponseEntity<>(
                        new ResponseRequest(HttpStatus.NOT_FOUND.value(), "Category ID tidak ditemukan"),
                            HttpStatus.NOT_FOUND

                    );
                }
            Product update =  productRepo.getReferenceById(id);
            update.setTitle(pr.getTitle());
            update.setImage(pr.getImage());
            update.setPrice(pr.getPrice());
            update.setCategory(pr.getCategory());
            Product result = productRepo.save(update);
            return new ResponseEntity<>(
                    new ResponseRequest(HttpStatus.OK.value(),
                            "Data berhasil diupdate", result),
                    HttpStatus.OK
                    );
        } catch (Exception e){
            return new ResponseEntity<>(
                    new ResponseRequest(HttpStatus.BAD_REQUEST.value(),
                            "Gagal akses", e),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    public ResponseRequest deleteAllProduct() {
        productRepo.deleteAll();
        return new ResponseRequest(HttpStatus.OK.value(), "Data berhasil dihapus");
    }


}
