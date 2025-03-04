package cz.jaro.transaction.repository;

import cz.jaro.transaction.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepository extends JpaRepository<Statement, Long> {

}
