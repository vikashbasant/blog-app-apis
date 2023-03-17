package co.blog.controller;

import co.blog.exception.GeneralException;
import co.blog.payloads.Response;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionController extends ResponseEntityExceptionHandler implements RequestBodyAdvice {

    /*----Handle The Custom HttpMessageNotReadable Exception----*/
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable (
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("===: ExceptionController:: Inside handleHttpMessageNotReadable Method :===");
        return ResponseEntity.ok().body(getResponse("HMNR - " + ex.getLocalizedMessage(), "400"));
    }

    /*----Handle The Custom MethodArgumentNotValid Exception.----*/
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("===: ExceptionController:: Inside handleMethodArgumentNotValid Method :===");
        // Here we are getting all the error which is invalid as per our java bean Validation
        List<String> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());
        // returning 400 Bad Request in HTTP STATUS and all the invalid parameter details in body
        return ResponseEntity.ok().body(getResponse(String.valueOf(validationErrors), "400"));
    }


    /*----Handle The Custom ServletRequestBinding Exception.----*/
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException (ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("===: ExceptionController:: Inside handleServletRequestBindingException Method :===");
        return ResponseEntity.ok().body(getResponse("SRBE - " + ex.getLocalizedMessage(), "400"));
    }

    /*----Handle The Custom ConstraintViolationException Exception.----*/
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException (Exception ex) {

        log.info("===: ExceptionController:: Inside handleDataIntegrityViolationException Method :===");
        String message = ex.getMessage();
        return new ResponseEntity<>(getResponse(message, "BLOGS_500"), HttpStatus.CONFLICT);
    }

    /*----Handle The Custom RuntimeException Exception.----*/
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> runtimeException (RuntimeException ex) {

        log.info("===: ExceptionController:: Inside runtimeException Method :===");
        String message = ex.getMessage();
        return new ResponseEntity<>(getResponse(message, "BLOGS_500"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*----Handle The Custom GeneralException Exception.----*/
    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<Object> generalException (GeneralException gExcp) {
        log.info("===: ExceptionController:: Inside generalException Method :===");
        String message = gExcp.getMessage();
        return new ResponseEntity<>(getResponse(message, "BLOGS_400"), HttpStatus.BAD_REQUEST);
    }


    /*----Handle The Custom SQLException Exception.----*/
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException (final SQLException ex) {
        log.info("===: ExceptionController:: Inside handleSQLException Method :===");
        return ResponseEntity.ok().body(getResponse(ex.getMessage(), "SQL"));
    }

    /*----Handle The Custom IllegalArgumentException Exception.----*/
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException (final IllegalArgumentException ex) {
        log.info("===: ExceptionController:: Inside handleIllegalArgumentException Method :===");
        return ResponseEntity.ok().body(getResponse(ex.getMessage(), "IAE"));
    }

    /*----Handle The Custom IOException Exception.----*/
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException (final IOException ex) {
        log.info("===: ExceptionController:: Inside handleIOException Method :===");
        return ResponseEntity.ok().body(getResponse(ex.getMessage(), "IOE"));
    }

    /*----Handle The Custom Exception.----*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException (final Exception ex) {
        log.info("===: ExceptionController:: Inside handleAllException Method :===");
        ex.printStackTrace();
        return ResponseEntity.ok().body(getResponse(ex.getMessage(), "500"));
    }


    /*----- Handle The Custom ExpiredJwtException----*/
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(final Exception ex) {
        log.info("===: ExceptionController:: Inside handleExpiredJwtException Method :===");

        return ResponseEntity.ok().body(getResponse(ex.getMessage(), "401"));
    }


    /*----- Handle The Custom MalformedJwtException----*/
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwtException(final Exception ex) {
        log.info("===: ExceptionController:: Inside handleMalformedJwtException Method :===");
        return ResponseEntity.ok().body(getResponse(ex.getMessage(), "500"));
    }


    /*----- Handle The Custom MalformedJwtException----*/
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(final Exception ex) {
        log.info("===: ExceptionController:: Inside handleBadCredentialsException Method :===");
        return ResponseEntity.ok().body(getResponse(ex.getMessage(), "401"));
    }

    //Invoked first to determine if this interceptor applies.
    @Override
    public boolean supports (MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("===: ExceptionController:: Inside supports Method :===");
        return true;// false
    }

    //Invoked second before the request body is read and converted.
    @Override
    public HttpInputMessage beforeBodyRead (HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("===: ExceptionController:: Inside beforeBodyRead Method :===");
        return inputMessage;//null
    }

    //Invoked third (and last) after the request body is converted to an Object.
    @Override
    public Object afterBodyRead (Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("===: ExceptionController:: Inside afterBodyRead Method :===");
        return body; //null
    }

    //Invoked second (and last) if the body is empty.
    @Override
    public Object handleEmptyBody (Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("===: ExceptionController:: Inside handleEmptyBody Method :===");
        return body;//null
    }


    /*----Template For Response----*/
    private Response getResponse (String message, String code) {
        Response response = new Response();
        response.setStatus("FAILURE");
        response.setErrorMessage(message);
        response.setStatusCode(code);
        response.setResponseType("E");
        return response;
    }
}
