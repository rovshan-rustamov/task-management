package com.example.taskmanagement.exception;

import com.example.taskmanagement.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    private final TranslationService translationService;


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest req) {
        var lang = req.getHeader(ACCEPT_LANGUAGE) == null ? "en" : req.getHeader(ACCEPT_LANGUAGE);
           ex.printStackTrace();
           return ResponseEntity.status(400).body(ErrorResponse.builder()
                           .status(400)
                           .title("Exception")
                           .details(translationService.findByKey(ex.getErrorCode().name(), lang, ex.getArguments()))
                   .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                               WebRequest req) {
        ex.printStackTrace();
        ErrorResponse response = ErrorResponse.builder()
                .status(400)
                .title("Exception")
                .build();
        ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach(error -> {
                    Map<String, String> data = response.getData();
                    data.put(error.getField(), error.getDefaultMessage());
                });
        return ResponseEntity.status(400).body(response);
    }

}
