package com.tistory.lky1001.configuration.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private int code;

    private String message;
}
