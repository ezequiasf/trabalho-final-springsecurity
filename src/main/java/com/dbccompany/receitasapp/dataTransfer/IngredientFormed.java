package com.dbccompany.receitasapp.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientFormed extends IngredientCreate {
    private Integer idIngredient;
}
