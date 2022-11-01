package com.example.ApiPrueba.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestApiError {

    HttpStatus httpStatus;
    String errorMessage;
    String errorDetails;



}
