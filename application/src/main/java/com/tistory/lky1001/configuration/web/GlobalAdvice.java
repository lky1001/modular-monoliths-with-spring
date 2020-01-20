package com.tistory.lky1001.configuration.web;

import com.tistory.lky1001.buildingblocks.domain.DomainValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.net.SocketTimeoutException;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalAdvice {

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<ExceptionResponse> domainExceptionHandler(DomainValidationException e, HttpServletRequest request) {
        logger.debug(e.getMessage(), e);
        return createExceptionResponse(e.getCode(), e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> superExceptionHandler(Exception e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        return createExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return createExceptionResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<ExceptionResponse> timeoutException(Exception e) {
        logger.error(e.getMessage(), e);
        return createExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler({NoHandlerFoundException.class, ConversionNotSupportedException.class, BindException.class,
            TypeMismatchException.class,
            MissingServletRequestParameterException.class, MethodArgumentNotValidException.class,
            HttpRequestMethodNotSupportedException.class, HttpMessageNotWritableException.class,
            HttpMessageNotReadableException.class, HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class, MissingServletRequestPartException.class})
    public ResponseEntity<ExceptionResponse> httpExceptionHandler(Exception e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        return createExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionResponse> createExceptionResponse(int code, String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ExceptionResponse(code, message), httpStatus);
    }
}
