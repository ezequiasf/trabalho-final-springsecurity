package com.dbccompany.receitasapp.dataTransferQuery;

import com.dbccompany.receitasapp.dataTransfer.IngredientFormed;
import com.dbccompany.receitasapp.dataTransfer.RecipeCreate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientDTO extends RecipeCreate {
    private Long idRecipe;
    private List<IngredientFormed> ingredientFormeds;
}
