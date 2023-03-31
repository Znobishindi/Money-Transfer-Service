package ru.netology.moneytransferservice.exception;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class ErrorInputData extends RuntimeException {
    private final long id;

    public ErrorInputData(String message, long id) {
        super(message);
        this.id = id;
    }
}
