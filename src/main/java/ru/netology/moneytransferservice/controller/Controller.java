package ru.netology.moneytransferservice.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytransferservice.model.ConfirmOperation;
import ru.netology.moneytransferservice.model.Defendant;
import ru.netology.moneytransferservice.model.TransferFromCardToCard;
import ru.netology.moneytransferservice.service.MoneyTransferService;

@org.springframework.stereotype.Controller
@CrossOrigin(origins = "https://serp-ya.github.io", methods = RequestMethod.POST)
@RequestMapping("/")
@RestController
public class Controller {
    private final MoneyTransferService service;

    @Autowired
    public Controller(MoneyTransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public Defendant saveMoneyTransfer( @Valid @RequestBody TransferFromCardToCard transfer) {
        return service.saveMoneyTransfer(transfer);
    }

    @PostMapping("/confirmOperation")
    public Defendant saveConfirmOperation(@Valid @RequestBody ConfirmOperation confirmOperation) {
        return service.saveConfirmOperation(confirmOperation);
    }
}
