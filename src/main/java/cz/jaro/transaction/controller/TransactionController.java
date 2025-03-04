package cz.jaro.transaction.controller;

import cz.jaro.transaction.mapper.TransactionMapper;
import cz.jaro.transaction.model.Transaction;
import cz.jaro.transaction.model.dto.TransactionDTO;
import cz.jaro.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    TransactionMapper mapper;

    @GetMapping("/{ownAccountNumber}/transactions")
    public List<TransactionDTO> transactions(@PathVariable String ownAccountNumber) {
        List<Transaction> transactions = service.transactions(ownAccountNumber);
        List<TransactionDTO> transactionsDTO = mapper.toTransactionDTO(transactions);
        return transactionsDTO;
    }

    // For testing
    @GetMapping("/{ownAccountNumber}/createTransaction")
    public Transaction createTransaction(@PathVariable String ownAccountNumber) {
        return service.createTransaction(ownAccountNumber);
    }

}
