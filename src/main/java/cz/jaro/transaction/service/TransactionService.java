package cz.jaro.transaction.service;

import cz.jaro.transaction.model.Account;
import cz.jaro.transaction.model.Statement;
import cz.jaro.transaction.model.Transaction;
import cz.jaro.transaction.model.TransactionType;
import cz.jaro.transaction.repository.AccountRepository;
import cz.jaro.transaction.repository.StatementRepository;
import cz.jaro.transaction.repository.TransactionRepository;
import cz.jaro.transaction.repository.TransactionTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private StatementRepository statementRepository;

    public List<Transaction> transactions(String ownAccountNumber) {
        return transactionRepository.findAllByOwnAccountNumber(ownAccountNumber);
    }

    @Transactional
    public Transaction createTransaction(String ownAccountNumber) {
        // Generate the TrxId
        // Use the maximum existing TrxId + 1
        Long maxTrxId = null;
        for (Transaction transaction : transactionRepository.findAll()) {
            if (maxTrxId == null || maxTrxId < transaction.getTrxId())
                maxTrxId = transaction.getTrxId();
        }

        // Create transaction
        Transaction transaction = new Transaction();

        transaction.setTrxId(maxTrxId + 1);
        int whole = new Random().nextInt(100);
        int decimal = new Random().nextInt(100);
        transaction.setAmount(new BigDecimal(whole + "." + decimal));
        transaction.setCurrency("CZK");
        transaction.setId("Id");
        transaction.setBankref("Bankref");
        transaction.setTransactionId("TransactionId");
        transaction.setBookingDate(LocalDate.now());
        transaction.setPostingDate(LocalDate.now());
        transaction.setCreditDebitIndicator("+");
        transaction.setOwnAccountNumber(ownAccountNumber);
        transaction.setDetail1("Detail1");
        transaction.setDetail2("Detail2");
        transaction.setDetail3("Detail3");
        transaction.setDetail4(null);
        transaction.setProductBankRef("ProductBankRef");
        transaction.setConstantSymbol("KS");
        transaction.setSpecificSymbol("SS");
        transaction.setVariableSymbol("VS");

        // Foreign entities - just use an existing entity
        Account counterPartyAccount = accountRepository.findAll().get(0);
        TransactionType transactionType = transactionTypeRepository.findAll().get(0);
        Statement statement = statementRepository.findAll().get(0);

        transaction.setCounterPartyAccount(counterPartyAccount);
        transaction.setTransactionType(transactionType);
        transaction.setStatement(statement);

        return transactionRepository.save(transaction);
    }
}
