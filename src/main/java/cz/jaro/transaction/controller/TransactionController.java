package cz.jaro.transaction.controller;

import cz.jaro.transaction.model.Transaction;
import cz.jaro.transaction.model.dto.AccountDTO;
import cz.jaro.transaction.model.dto.AmountDTO;
import cz.jaro.transaction.model.dto.DetailsDTO;
import cz.jaro.transaction.model.dto.TransactionDTO;
import cz.jaro.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping("/{ownAccountNumber}/transactions")
    public List<TransactionDTO> transactions(@PathVariable String ownAccountNumber) {
        List<Transaction> transactions = service.transactions(ownAccountNumber);
        List<TransactionDTO> transactionsDTO = transaction2transactionDTO(transactions);
        return transactionsDTO;
    }

    // For testing
    @GetMapping("/{ownAccountNumber}/createTransaction")
    public Transaction createTransaction(@PathVariable String ownAccountNumber) {
        return service.createTransaction(ownAccountNumber);
    }

    List<TransactionDTO> transaction2transactionDTO(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDTOs.add(transaction2transactionDTO(transaction));
        }

        return transactionDTOs;
    }

    TransactionDTO transaction2transactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setAmount(new AmountDTO(transaction.getCurrency(), transaction.getAmount()));
        transactionDTO.setBankref(transaction.getBankref());
        transactionDTO.setBookingDate(transaction.getBookingDate()); // TODO format YYYY-MM-DD
        transactionDTO.setCounterPartyAccount(new AccountDTO(transaction.getCounterPartyAccount().getName(), transaction.getCounterPartyAccount().getNumber(), transaction.getCounterPartyAccount().getCode()));
        transactionDTO.setCreditDebitIndicator(transaction.getCreditDebitIndicator());
        transactionDTO.setDetails(new DetailsDTO(transaction.getDetail1(), transaction.getDetail2(), transaction.getDetail3(), transaction.getDetail4()));
        transactionDTO.setId(transaction.getId());
        transactionDTO.setOwnAccountNumber(transaction.getOwnAccountNumber());
        transactionDTO.setPostingDate(transaction.getPostingDate()); // TODO format YYYY-MM-DD
        transactionDTO.setProductBankRef(transaction.getProductBankRef());
        transactionDTO.setSpecificSymbol(transaction.getSpecificSymbol());

        transactionDTO.setStatementNumber(transaction.getStatement().getNumber());
        transactionDTO.setStatementPeriod(transaction.getStatement().getPeriod());

        transactionDTO.setTransactionId(transaction.getTransactionId());

        transactionDTO.setTransactionType(transaction.getTransactionType().getType());
        transactionDTO.setTransactionTypeCode(transaction.getTransactionType().getCode());

        transactionDTO.setVariableSymbol(transaction.getVariableSymbol());

        return transactionDTO;
    }
}
