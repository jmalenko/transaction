package cz.jaro.transaction.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO {

    private AmountDTO amount;

    private String id;

    private String bankref;

    private String transactionId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date postingDate;

    private String creditDebitIndicator;

    private String ownAccountNumber;

    private AccountDTO counterPartyAccount;

    private DetailsDTO details;

    private String productBankRef;

    private String statementNumber;
    private String statementPeriod;

    private String transactionType;
    private Integer transactionTypeCode;

    private String specificSymbol;

    private String variableSymbol;

}

