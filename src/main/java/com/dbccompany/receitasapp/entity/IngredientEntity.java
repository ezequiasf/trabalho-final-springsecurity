package com.dbccompany.receitasapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ingredient")
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ING_GEN")
    @SequenceGenerator(name = "ING_GEN", sequenceName = "seq_ingredient", allocationSize = 1)
    @Column(name = "ingredient_id")
    private Long idIngredient;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private RecipeEntity recipeEntity;

    @Column(name = "ingredient_text")
    private String ingredient;
}
