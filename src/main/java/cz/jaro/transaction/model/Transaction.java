package cz.jaro.transaction.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "transaction")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @NotNull
    @Column(name = "trxId")
    private Long trxId;

    private BigDecimal amount;

    private String currency;

    private String id;

    private String bankref;

    @Column(name = "transactionId")
    private String transactionId;

    @Column(name = "bookingDate")
    private LocalDate bookingDate;

    @Column(name = "postingDate")
    private LocalDate postingDate;

    @Column(name = "creditDebitIndicator")
    private String creditDebitIndicator;

    @Column(name = "ownAccountNumber")
    private String ownAccountNumber;

    @ManyToOne
    @JoinColumn(name = "counterPartyAccount")
    private Account counterPartyAccount;

    private String detail1;

    private String detail2;

    private String detail3;

    private String detail4;

    @Column(name = "productBankRef")
    private String productBankRef;

    @ManyToOne
    @JoinColumn(name = "transactionType")
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "statement")
    private Statement statement;

    @Column(name = "constantSymbol")
    private String constantSymbol;

    @Column(name = "specificSymbol")
    private String specificSymbol;

    @Column(name = "variableSymbol")
    private String variableSymbol;

}

