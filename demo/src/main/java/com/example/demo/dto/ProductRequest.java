package com.example.demo.dto;


import com.example.demo.entity.Category;
import lombok.Data;


@Data
public class ProductRequest {

    private Integer id;
    private String title;
    private Integer price;
    private String image;
    private Category category;
}
