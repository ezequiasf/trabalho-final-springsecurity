package com.dbccompany.receitasapp.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeFormed extends RecipeCreate {
    private Integer idRecipe;
}
