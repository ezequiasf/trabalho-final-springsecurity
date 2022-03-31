package com.dbccompany.receitasapp.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IngredientCreate {
    @NotBlank(message = "Nome do ingrediente deve conter pelo menos um caractere.")
    @Size(min = 1, max = 100, message = "Nome do ingrediente deve conter no máximo 100 caracteres.")
    private String ingredient;
}
