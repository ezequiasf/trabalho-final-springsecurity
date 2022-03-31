package com.dbccompany.receitasapp.dataTransferQuery;

import com.dbccompany.receitasapp.dataTransfer.ComentFormed;
import com.dbccompany.receitasapp.dataTransfer.UserUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserComentDTO extends UserUpdate {
    private Long idUser;
    private List<ComentFormed> coments;
}
