package com.bci.test.demo.domain.exception;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserException extends Exception{

    private String message;
    private HttpStatus httpStatus;

}
