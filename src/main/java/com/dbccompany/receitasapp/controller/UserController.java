package com.dbccompany.receitasapp.controller;


import com.dbccompany.receitasapp.dataTransfer.UserCreate;
import com.dbccompany.receitasapp.dataTransfer.UserFormed;
import com.dbccompany.receitasapp.dataTransfer.UserUpdate;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService serviceUsuario;

    @ApiOperation(value = "Retorna uma lista de usuários registrados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Os usuários foram listadas com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema."),})
    @GetMapping("/readAllUsers")
    public List<UserFormed> readAllUsers() {
        return serviceUsuario.readAllUsers();
    }

    @ApiOperation(value = "Encontra um usuário registrado através do id informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "O usuário foi informado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/{idUser}")
    public UserFormed findUserById(@PathVariable("idUser") Long idUser) throws ObjectNotFoundException {
        return serviceUsuario.findUserById(idUser);
    }

    @ApiOperation(value = "Cadastra um usuário no banco de dados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cadastrou o usuário com sucesso no banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @PostMapping("/saveUser")
    @Validated
    public UserFormed saveUser(@Valid @RequestBody UserCreate userCreate) {
        return serviceUsuario.saveUser(userCreate);
    }

    @ApiOperation(value = "Atualiza um usuário no banco de dados através do id informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Atualizou com sucesso o usuário no banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @PutMapping("/updateUser/{idUser}")
    @Validated
    public UserFormed updateUser(@PathVariable("idUser") Long idUser,
                                 @Valid @RequestBody UserUpdate userUpdate) throws ObjectNotFoundException {
        return serviceUsuario.updateUser(userUpdate, idUser);
    }

    @ApiOperation(value = "Deleta um usuário do banco de dados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deletou o usuáro com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @DeleteMapping("/deleteUser/{idUser}")
    public void deleteUser(@PathVariable("idUser") Long idUser) throws ObjectNotFoundException {
        serviceUsuario.deleteUser(idUser);
    }
}
