package ru.netology.moneytransferservice.exception;

import lombok.Getter;

@Getter
public class ErrorConfirmation extends RuntimeException {
    private final long id;

    public ErrorConfirmation(String message, long id) {
        super(message);
        this.id = id;
    }
}
