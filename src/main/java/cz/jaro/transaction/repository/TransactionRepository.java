package cz.jaro.transaction.repository;

import cz.jaro.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByOwnAccountNumber(String ownAccountNumber);

}
