package com.dbccompany.receitasapp.controller;

import com.dbccompany.receitasapp.dataTransfer.UserFormed;
import com.dbccompany.receitasapp.dataTransferQuery.UserComentDTO;
import com.dbccompany.receitasapp.dataTransferQuery.UserRatingDTO;
import com.dbccompany.receitasapp.dataTransferQuery.UserRecipeDTO;
import com.dbccompany.receitasapp.service.UserQueriesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/queryUser")
@RequiredArgsConstructor
public class UserQueriesController {
    private final UserQueriesService userQueriesService;

    @ApiOperation(value = "Retorna uma lista de comentários do usuário informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de comentários do usuário retornada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findComentsOfUser")
    public List<UserComentDTO> findComentsOfUser(@RequestParam(required = false) Long userId) {
        return userQueriesService.findComentsOfUser(userId);
    }

    @ApiOperation(value = "Retorna uma lista de notas do usuário informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de notas do usuário retornada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findRatingsOfUser")
    public List<UserRatingDTO> findRatingsOfUser(@RequestParam(required = false) Long userId) {
        return userQueriesService.findRatingsOfUser(userId);
    }

    @ApiOperation(value = "Retorna uma lista de receitas do usuário informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de receitas do usuário retornada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findRecipesOfUser")
    public List<UserRecipeDTO> findRecipesOfUser(@RequestParam(required = false) Long userId) {
        return userQueriesService.findRecipesOfUser(userId);
    }

    @ApiOperation(value = "Retorna uma lista de usuários que tenham o nome informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de usuários retornada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findUserByName")
    public List<UserFormed> findByName(@RequestParam String userName) {
        return userQueriesService.findByNameContainingIgnoreCase(userName);
    }

    @ApiOperation(value = "Retorna uma lista de usuários ordenada pelo nome.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de usuários retornada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findAllUsersOrderByName")
    public List<UserFormed> findAllUsersOrderByName() {
        return userQueriesService.listAllUsersOrderByName();
    }
}
