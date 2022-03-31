package com.dbccompany.receitasapp.dataTransferQuery;

import com.dbccompany.receitasapp.dataTransfer.RatingFormed;
import com.dbccompany.receitasapp.dataTransfer.RecipeCreate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecipeRatingDTO extends RecipeCreate {
    private Long idRecipe;
    private List<RatingFormed> ratings;
}
