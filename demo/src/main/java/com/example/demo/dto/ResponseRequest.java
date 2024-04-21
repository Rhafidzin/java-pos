package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseRequest {
    private int status;
    private String message;
    private Object data;

    public ResponseRequest(int status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseRequest(int status, String message){
        this.status = status;
        this.message = message;
    }


}
