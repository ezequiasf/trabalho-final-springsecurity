package com.dbccompany.receitasapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECIPE_GEN")
    @SequenceGenerator(name = "RECIPE_GEN", sequenceName = "seq_recipe", allocationSize = 1)
    @Column(name = "recipe_id")
    private Long idRecipe;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "prepare_recipe")
    private String prepareRecipe;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "prepare_time")
    private Integer prepareTime;

    @Column(name = "calories")
    private BigDecimal calories;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "recipeEntity", cascade = CascadeType.ALL)
    private Set<IngredientEntity> ingredientEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<ComentEntity> comentEntities;

    @JsonIgnore
    @OneToMany(mappedBy = "recipeEntity", cascade = CascadeType.ALL)
    private Set<RatingEntity> ratingEntities;
}
