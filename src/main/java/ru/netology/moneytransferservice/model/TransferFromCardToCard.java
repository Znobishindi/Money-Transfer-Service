package ru.netology.moneytransferservice.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferFromCardToCard {

    private long id;
    @Size(max = 16, min = 16, message = "Must be 16 character")
    private String cardFromNumber;
    @Pattern(regexp = "[0-1][0-9]/2[3-9]", message = "Must be MM/YY, but mot early then 01/23")
    private String cardFromValidTill;
    @Size(max = 3, min = 3, message = "Must be 3 character")
    private String cardFromCVV;
    @Size(max = 16, min = 16, message = "Must be 16 character")
    private String cardToNumber;
    @Valid
    private Amount amount;

    private ConfirmOperation confirmOperation;

    private Status status;

    public TransferFromCardToCard(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransferFromCardToCard{" +
                "id=" + id +
                ", cardFromNumber='" + cardFromNumber + '\'' +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                ", cardFromCVV='" + cardFromCVV + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' + amount +
                '}';
    }
}
