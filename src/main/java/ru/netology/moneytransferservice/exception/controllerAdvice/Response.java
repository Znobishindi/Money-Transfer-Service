package ru.netology.moneytransferservice.exception.controllerAdvice;

public class Response {

    private String message;
    private long id;

    public Response() {
    }

    public Response(String message,long id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
