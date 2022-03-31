package com.dbccompany.receitasapp.dataTransferQuery;

import com.dbccompany.receitasapp.dataTransfer.RecipeFormed;
import com.dbccompany.receitasapp.dataTransfer.UserUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRecipeDTO extends UserUpdate {
    private Long idUser;
    private List<RecipeFormed> recipes;
}
