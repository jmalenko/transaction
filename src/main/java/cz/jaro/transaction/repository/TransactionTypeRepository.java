package cz.jaro.transaction.repository;

import cz.jaro.transaction.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

}
