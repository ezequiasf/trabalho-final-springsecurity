package com.dbccompany.receitasapp.controller;

import com.dbccompany.receitasapp.dataTransfer.IngredientCreate;
import com.dbccompany.receitasapp.dataTransfer.IngredientFormed;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.service.IngredientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @ApiOperation(value = "Retorna uma lista de ingredientes registrados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Os ingredientes foram listados com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema."),})
    @GetMapping("/readAllIngredients")
    public List<IngredientFormed> readAllIngredients() {
        return ingredientService.readAllIngredients();
    }

    @ApiOperation(value = "Encontra um ingrediente registrado através do id informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "O ingrediente foi informado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/{idIngredient}")
    public IngredientFormed findIngredientById(@PathVariable("idIngredient") Long idIngredient)
            throws ObjectNotFoundException {
        return ingredientService.findIngredientById(idIngredient);
    }

    @ApiOperation(value = "Cadastra um ingrediente no banco de dados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cadastrou o ingrediente com sucesso no banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @PostMapping("/saveIngredient/{idRecipe}")
    public IngredientFormed saveIngredient(@Valid @RequestBody IngredientCreate ingDto
            , @PathVariable("idRecipe") Long idRecipe) throws ObjectNotFoundException {
        return ingredientService.saveIngredient(ingDto, idRecipe);
    }

    @ApiOperation(value = "Atualiza um ingrediente no banco de dados através do id informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Atualizou com sucesso o ingrediente no banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @PutMapping("/updateIngredient/{idIngredient}")
    public IngredientFormed updateIngredient(@Valid @RequestBody IngredientCreate ingDto
            , @PathVariable("idIngredient") Long idIngrediente) throws ObjectNotFoundException {
        return ingredientService.updateIngredient(ingDto, idIngrediente);
    }

    @ApiOperation(value = "Deleta um ingrediente do banco de dados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deletou o ingrediente com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @DeleteMapping("/deleteIngredient/{idIngredient}")
    public void deleteIngredient(@PathVariable("idIngredient") Long idIng) throws ObjectNotFoundException {
        ingredientService.deleteIngredient(idIng);
    }
}
