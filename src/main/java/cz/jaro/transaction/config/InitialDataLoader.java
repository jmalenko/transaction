package cz.jaro.transaction.config;

import cz.jaro.transaction.model.Account;
import cz.jaro.transaction.model.Statement;
import cz.jaro.transaction.model.Transaction;
import cz.jaro.transaction.model.TransactionType;
import cz.jaro.transaction.repository.AccountRepository;
import cz.jaro.transaction.repository.StatementRepository;
import cz.jaro.transaction.repository.TransactionRepository;
import cz.jaro.transaction.repository.TransactionTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Slf4j

@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private StatementRepository statementRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        transactionRepository.deleteAll();

        if (transactionRepository.count() == 0) {
            log.info("Initializing database");

            // Build some entities

            Account counterPartyAccount = new Account(1L, "PPF BANKA A.S.", "0000009504010019", "6000");
            counterPartyAccount = accountRepository.save(counterPartyAccount);

            Statement statement = new Statement(1L, "196", "2022", ""); // description is not in output
            statement = statementRepository.save(statement);

            TransactionType transactionType = new TransactionType(1L, "DPO", 1012209);
            transactionType = transactionTypeRepository.save(transactionType);

            Transaction transaction = new Transaction();

            transaction.setTrxId(1L);
            transaction.setAmount(new BigDecimal("1500"));
            transaction.setCurrency("CZK");
            transaction.setId("20221019:0000000219");
            transaction.setBankref("PS221019SO314822");
            transaction.setTransactionId("4831716");
            transaction.setBookingDate(null); // "2022-10-19"
            transaction.setPostingDate(new Date()); // "2022-10-19"
            transaction.setCreditDebitIndicator("CRDT");
            transaction.setOwnAccountNumber("2002222222");
            transaction.setCounterPartyAccount(counterPartyAccount);
            transaction.setDetail1("Posílám peníze");
            transaction.setDetail2(null); // in out put only when not null
            transaction.setDetail3(null);
            transaction.setDetail4(null);
            transaction.setProductBankRef("PS221019SO314822");
            transaction.setTransactionType(transactionType);
            transaction.setStatement(statement);
            transaction.setConstantSymbol(null); // not in output
            transaction.setSpecificSymbol("12");
            transaction.setVariableSymbol("12");

            transactionRepository.save(transaction);
        }
    }

}
