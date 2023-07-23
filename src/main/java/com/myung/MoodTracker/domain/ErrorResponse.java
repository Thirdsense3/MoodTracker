package com.myung.MoodTracker.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class ErrorResponse {
    private Integer code;
    private List<String> errors;
    private String message;

    public static ErrorResponse of(Integer code, List<String> errors, String message) {
        return new ErrorResponse(code, errors, message);
    }
}
