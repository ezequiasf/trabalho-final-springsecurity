package com.dbccompany.receitasapp.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @NotBlank(message = "O usuário deve ser informado.")
    @Size(min = 1, max = 10, message = "O nome de usuário deve ter entre 1 e 10 caracteres.")
    private String userName;

    @NotBlank(message = "A senha deve ser informada.")
    @Size(min = 5, max = 10, message = "A senha deve ter entre 5 e 10 caracteres.")
    private String password;
}
