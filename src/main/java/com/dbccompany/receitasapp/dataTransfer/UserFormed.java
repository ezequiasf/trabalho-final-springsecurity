package com.dbccompany.receitasapp.dataTransfer;

import com.dbccompany.receitasapp.entity.RoleType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserFormed {
    private Long idUser;

    private String username;

    private Boolean isActive;

    private RoleType roleType;
}
