package com.example.demo.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryRequest {

    private Integer id;
    private String name;

    public CategoryRequest(Integer id) {
        this.id = id;
    }
}
