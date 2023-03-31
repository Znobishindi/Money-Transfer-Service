package ru.netology.moneytransferservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.moneytransferservice.model.ConfirmOperation;
import ru.netology.moneytransferservice.model.Defendant;
import ru.netology.moneytransferservice.model.TransferFromCardToCard;
import ru.netology.moneytransferservice.repository.MoneyTransferServiceRepository;

@Service
public class MoneyTransferService {
    private final MoneyTransferServiceRepository repository;

    @Autowired
    public MoneyTransferService(MoneyTransferServiceRepository repository) {
        this.repository = repository;
    }

    public Defendant saveMoneyTransfer(TransferFromCardToCard transfer) {
        return repository.saveMoneyTransfer(transfer);
    }
    public Defendant saveConfirmOperation(ConfirmOperation confirmOperation) {
        return repository.saveConfirmOperation(confirmOperation);
    }
}
