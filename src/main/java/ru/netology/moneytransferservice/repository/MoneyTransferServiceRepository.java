package ru.netology.moneytransferservice.repository;


import lombok.extern.slf4j.Slf4j;
import ru.netology.moneytransferservice.exception.ErrorConfirmation;
import ru.netology.moneytransferservice.exception.ErrorInputData;
import ru.netology.moneytransferservice.exception.ErrorTransfer;
import ru.netology.moneytransferservice.model.ConfirmOperation;
import ru.netology.moneytransferservice.model.Defendant;
import ru.netology.moneytransferservice.model.Status;
import ru.netology.moneytransferservice.model.TransferFromCardToCard;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.stereotype.Repository

@Slf4j
public class MoneyTransferServiceRepository {
    private final Map<Long, TransferFromCardToCard> moneyTransferMap = new ConcurrentHashMap<>();
    private final AtomicLong countMoneyTransfer = new AtomicLong(0);



    public Defendant saveMoneyTransfer(TransferFromCardToCard transfer) {
        long id = countMoneyTransfer.incrementAndGet();
        transfer.setId(id);
        double trueValue = transfer.getAmount().getValue() * 0.01;
        transfer.getAmount().setValue(trueValue);
        moneyTransferMap.put(id, transfer);
        transfer.setStatus(Status.CREATED);
        String operationId = Long.toString(id);
        log.debug("Money Transfer successful {} operationId = {}",
                transfer.getStatus(), operationId);
//       transfer.setStatus(Status.EXCEPTION);
        if (!transfer.getStatus().equals(Status.CREATED)) {

            throw new ErrorTransfer("The transfer was not created. Please try again", id);
        }
        return new Defendant(operationId);
    }

    public Defendant saveConfirmOperation(ConfirmOperation confirmOperation) {
        long id = Long.parseLong(confirmOperation.getOperationId());
        if (!moneyTransferMap.containsKey(id)) {
            throw new ErrorInputData("Not found operation by id " + id, id);
        }
        TransferFromCardToCard transfer = moneyTransferMap.get(id);
        if (transfer.getConfirmOperation() == null) {
            confirmOperation.setCommission(transfer.getAmount().getValue() * 0.01);
            transfer.setConfirmOperation(confirmOperation);
            transfer.setStatus(Status.CONFIRMED);
            moneyTransferMap.put(id, transfer);
                        log.debug("Money Transfer successful {}, Transfer:{} Commission:{}",
                                transfer.getStatus(),
                                transfer, transfer.getConfirmOperation().getCommission());
        } else {
            throw new ErrorInputData("The transfer has already been confirmed", id);
        }
//        transfer.setStatus(Status.EXCEPTION);
        if (!transfer.getStatus().equals(Status.CONFIRMED)) {
            throw new ErrorConfirmation("The transfer was not confirmed. Please try again", id);
        }
        return new Defendant(Long.toString(id));
    }


}
