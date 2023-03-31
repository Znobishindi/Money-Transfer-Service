package ru.netology.moneytransferservice.repository;


import org.junit.jupiter.api.Test;
import ru.netology.moneytransferservice.exception.ErrorInputData;
import ru.netology.moneytransferservice.model.Amount;
import ru.netology.moneytransferservice.model.ConfirmOperation;
import ru.netology.moneytransferservice.model.TransferFromCardToCard;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTransferServiceRepositoryTest {


    private final MoneyTransferServiceRepository moneyTransferServiceRepository = new MoneyTransferServiceRepository();
    private final TransferFromCardToCard transfer = new TransferFromCardToCard(
            "1111222244445555",
            "12/23",
            "075",
            "9999888877776666",
            new Amount(1000.0, "RUB"));

    private final Map<Long, TransferFromCardToCard> moneyTransferMap = new ConcurrentHashMap<>();

    private final ConfirmOperation confirmOperation = new ConfirmOperation("2","1111");

    @Test
    void saveMoneyTransferTestID() {
        long testID = 1;
        transfer.setId(testID);
        assertEquals(testID, transfer.getId());
    }

    @Test
    void saveMoneyTransferTestMAP() {
        long testID = 1;
        transfer.setId(testID);
        moneyTransferMap.put(testID, transfer);
        assertEquals(transfer, moneyTransferMap.get(testID));
    }

    @Test
    void saveConfirmOperationExceptionTest() {
        Exception exception = assertThrows(ErrorInputData.class, ()->
                moneyTransferServiceRepository.saveConfirmOperation(confirmOperation));
        assertEquals("Not found operation by id 2", exception.getMessage());
    }
    @Test
    void saveConfirmOperationCommissionTest() {
        confirmOperation.setCommission(transfer.getAmount().getValue() * 0.01);
        transfer.setConfirmOperation(confirmOperation);
        assertEquals(10.0,transfer.getConfirmOperation().getCommission());
    }
}