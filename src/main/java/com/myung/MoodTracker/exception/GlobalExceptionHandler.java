package com.myung.MoodTracker.exception;

import com.myung.MoodTracker.domain.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 나머지 Exception 경우 발생 : 500
     *
     * @param ex Exception
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception ex, BindingResult bindingResult) {
        log.error("Exception", ex);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        final ErrorResponse errorResponse = ErrorResponse.of(status.value(), errors, status.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * Body 데이터가 넘어오지 않았을 경우 : 400
     *
     * @param ex HttpMessageNotReadableException
     * @return ResponseEntity<ErrorResponse>
     */

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<?> handleBadRequestException(HttpMessageNotReadableException ex, BindingResult bindingResult) {
        log.error("handleBadRequestException", ex);

        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        final ErrorResponse errorResponse = ErrorResponse.of(status.value(), errors, status.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * request 데이터가 넘어오지 않았을 경우 : 400
     *
     * @param ex MissingServletRequestParameterException
     * @return ResponseEntity<ErrorResponse>
     */

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<?> handleNoHandlerFoundException(MissingServletRequestParameterException ex, BindingResult bindingResult) {
        log.error("handleNoHandlerFoundException", ex);

        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        final ErrorResponse errorResponse = ErrorResponse.of(status.value(), errors, status.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, status);
    }


    /**
     * 잘못된 주소로 요청 한 경우 : 404
     *
     * @param ex NoHandlerFoundException
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<?> handleNoHandlerFoundException(NoHandlerFoundException ex, BindingResult bindingResult) {
        log.error("handleNoHandlerFoundException", ex);

        HttpStatus status = HttpStatus.NOT_FOUND;
        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        final ErrorResponse errorResponse = ErrorResponse.of(status.value(), errors, status.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * Controller Valid 유효성 검사 실패한 경우 : 400
     *
     * @param ex MethodArgumentNotValidException
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, BindingResult bindingResult) {
        log.error("handleMethodArgumentNotValidException", ex);

        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        final ErrorResponse errorResponse = ErrorResponse.of(status.value(), errors, status.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * Entity, DTO 유효성 검사 실패한 경우 : 400
     *
     * @param ex ConstraintViolationException
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(ConstraintViolationException ex, BindingResult bindingResult) {
        log.error("ConstraintViolationException", ex);

        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        final ErrorResponse errorResponse = ErrorResponse.of(status.value(), errors, status.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, status);
    }
}

