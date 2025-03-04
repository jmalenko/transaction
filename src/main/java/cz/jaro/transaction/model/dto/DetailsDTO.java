package cz.jaro.transaction.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetailsDTO {

    private String detail1;

    private String detail2;

    private String detail3;

    private String detail4;

}
