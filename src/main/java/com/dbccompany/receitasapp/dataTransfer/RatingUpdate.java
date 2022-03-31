package com.dbccompany.receitasapp.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingUpdate {
    @NotNull(message = "A classificação deve ser informada.")
    @DecimalMax(value = "5.0", message = "O máximo da nota deve ser (5.0).")
    @DecimalMin(value = "0.0", message = "O mínimo da nota deve ser (0.0).")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal rating;
}
