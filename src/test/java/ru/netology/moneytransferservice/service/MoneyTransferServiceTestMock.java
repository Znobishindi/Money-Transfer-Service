package ru.netology.moneytransferservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.Defendant;
import ru.netology.moneytransferservice.model.TransferFromCardToCard;
import ru.netology.moneytransferservice.repository.MoneyTransferServiceRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MoneyTransferServiceTestMock {

    private final TransferFromCardToCard transfer = new TransferFromCardToCard(
            "1111222244445555",
            "12/23",
            "075",
            "9999888877776666",
            new Amount(1000.0, "RUB"));

    @Mock
    private MoneyTransferServiceRepository repository;

    @InjectMocks
    private MoneyTransferService moneyTransferService;

    @Test
    void saveMoneyTransferTest() {
        Defendant defendant = new Defendant("1");
        when(repository.saveMoneyTransfer(transfer)).thenReturn(defendant);
        assertEquals(defendant, moneyTransferService.saveMoneyTransfer(transfer));
    }

    @Test
    void saveConfirmOperationTest() {
        Defendant defendant = new Defendant("1");
        when(repository.saveConfirmOperation(transfer.getConfirmOperation())).thenReturn(defendant);
        assertEquals(defendant, moneyTransferService.saveConfirmOperation(transfer.getConfirmOperation()));
    }

}