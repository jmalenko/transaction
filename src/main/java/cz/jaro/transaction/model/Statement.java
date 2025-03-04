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
@Table(name = "statement")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Statement {
    @Id
    @NotNull
    @Column(name = "statementId")
    private Long statementId;

    private String number;

    private String period;

    private String description;

}
