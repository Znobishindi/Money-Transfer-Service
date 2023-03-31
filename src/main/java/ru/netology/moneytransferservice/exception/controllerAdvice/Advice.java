package ru.netology.moneytransferservice.exception.controllerAdvice;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.moneytransferservice.exception.ErrorConfirmation;
import ru.netology.moneytransferservice.exception.ErrorInputData;
import ru.netology.moneytransferservice.exception.ErrorTransfer;

@ControllerAdvice
public class Advice {

    @ExceptionHandler(ErrorInputData.class)
    public static ResponseEntity<Response> handleException400(ErrorInputData e) {
        Response response = new Response(e.getMessage(), e.getId());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(ErrorTransfer.class)
    public ResponseEntity<Response> handleException500(ErrorTransfer e) {
        Response response = new Response(e.getMessage(), e.getId());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(ErrorConfirmation.class)
    public ResponseEntity<Response> handleException500(ErrorConfirmation e) {
        Response response = new Response(e.getMessage(), e.getId());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(500));
    }
}
