package com.dbccompany.receitasapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "rating")
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATING_GEN")
    @SequenceGenerator(name = "RATING_GEN", sequenceName = "seq_rating", allocationSize = 1)
    @Column(name = "rating_id")
    private Long idRating;

    @Column(name = "recipe_id", updatable = false, insertable = false)
    private Long idRecipe;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private RecipeEntity recipeEntity;

    @Column(name = "rating")
    private BigDecimal rating;
}
