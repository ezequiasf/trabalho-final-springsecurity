package com.dbccompany.receitasapp.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingFormed extends RatingCreate {
    private Integer idRating;
}
