package co.blog.controller;

import co.blog.exception.GeneralException;

import co.blog.payloads.Response;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
public class ExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity runtimeException(RuntimeException ex){
        String message = ex.getMessage();

        Response response = new Response("USER_500", message);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity generalException(GeneralException gExcp){
        String message = gExcp.getMessage();
        String code = gExcp.getStatusCode();
        Object errorMessage = gExcp.getErrorMessages();

        Response response = new Response("FAILURE", code, message, errorMessage);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}
