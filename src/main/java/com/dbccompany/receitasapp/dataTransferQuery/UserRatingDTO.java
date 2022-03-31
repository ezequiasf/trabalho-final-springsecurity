package com.dbccompany.receitasapp.dataTransferQuery;

import com.dbccompany.receitasapp.dataTransfer.RatingFormed;
import com.dbccompany.receitasapp.dataTransfer.UserUpdate;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRatingDTO extends UserUpdate {
    private Long idUser;
    private List<RatingFormed> ratings;
}
