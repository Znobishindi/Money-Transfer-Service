package ru.netology.moneytransferservice.exception;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter

public class ErrorTransfer extends RuntimeException {
    private final long id;

    public ErrorTransfer(String message, long id) {
        super(message);
        this.id = id;
    }
}
