package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseRequest {
    private int statusCode;
    private HttpStatus httpStatus;
    private String message;
    private Object data;

    public ResponseRequest(int status, String message, Object data){
        this.statusCode = status;
        this.message = message;
        this.data = data;
    }

    public ResponseRequest(int status, String message){
        this.statusCode = status;
        this.message = message;
    }
    public ResponseRequest(HttpStatus httpStatus, String message, Object data){
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }
    public ResponseRequest(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public ResponseRequest(int statusCode ,HttpStatus httpStatus, String message){
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
