package com.example.springmvcexamples.example02.handlingexception.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyException extends RuntimeException {
    private int code;

    public MyException(String message) {
        super(message);
    }

    public MyException(int code, String message) {
        super(message);
        this.code = code;
    }
}
