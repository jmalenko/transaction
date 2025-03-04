package cz.jaro.transaction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactionType")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionType {
    @Id
    @NotNull
    @Column(name = "trxTypeId")
    private Long trxTypeId;

    private String type;

    private Integer code;
}
