package com.example.springmvcexamples.example02.handlingexception.exception;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {
    private int code;
    public MyException(int code, String message) {
        super(message);
        this.code = code;
    }
}
