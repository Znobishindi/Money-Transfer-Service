package ru.netology.moneytransferservice.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ConfirmOperation {
    @NotEmpty(message = "Empty operationId")
    @Positive(message = "OperationId must be > 0")
    private String operationId;
    @Size(min = 4, message = "Must be min 4 characters")
    @NotBlank(message = "Empty code")
    private String code;

    private double commission;

    public ConfirmOperation(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }
}
