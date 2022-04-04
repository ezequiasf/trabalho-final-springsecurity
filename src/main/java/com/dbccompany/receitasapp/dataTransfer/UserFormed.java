package com.dbccompany.receitasapp.dataTransfer;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserFormed {
    private Long idUser;

    private String username;
}
