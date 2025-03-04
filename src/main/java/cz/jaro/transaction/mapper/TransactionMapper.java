package cz.jaro.transaction.mapper;

import cz.jaro.transaction.model.Account;
import cz.jaro.transaction.model.Transaction;
import cz.jaro.transaction.model.dto.AccountDTO;
import cz.jaro.transaction.model.dto.TransactionDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "name", source = "accountName")
    @Mapping(target = "number", source = "accountNumber")
    @Mapping(target = "code", source = "bankCode")
    Account accountDTOToAccount(AccountDTO accountDTO);

    @InheritInverseConfiguration
    AccountDTO accountToAccountDTO(Account account);


    @Mapping(target = "amount.currency", source = "currency")
    @Mapping(target = "amount.value", source = "amount")
    @Mapping(target = "details.detail1", source = "detail1")
    @Mapping(target = "details.detail2", source = "detail2")
    @Mapping(target = "details.detail3", source = "detail3")
    @Mapping(target = "details.detail4", source = "detail4")
    @Mapping(target = "statementNumber", source = "statement.number")
    @Mapping(target = "statementPeriod", source = "statement.period")
    @Mapping(target = "transactionType", source = "transactionType.type")
    @Mapping(target = "transactionTypeCode", source = "transactionType.code")
    TransactionDTO toTransactionDTO(Transaction transaction);


    List<TransactionDTO> toTransactionDTO(Collection<Transaction> transactions);

}