package com.dbccompany.receitasapp.controller;

import com.dbccompany.receitasapp.dataTransfer.RecipeFormed;
import com.dbccompany.receitasapp.dataTransferQuery.RecipeComentDTO;
import com.dbccompany.receitasapp.dataTransferQuery.RecipeIngredientDTO;
import com.dbccompany.receitasapp.dataTransferQuery.RecipeRatingDTO;
import com.dbccompany.receitasapp.service.RecipeQueriesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/queryRecipe")
@RequiredArgsConstructor
public class RecipeQueriesController {
    private final RecipeQueriesService recipeQueriesService;

    @ApiOperation(value = "Retorna uma lista de receitas pelo preço maximo desejado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de receitas pelo seu preço maximo retornada com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findRecipeByPriceMax")
    public List<RecipeFormed> findByPrecoMax(@RequestParam BigDecimal price) {
        return recipeQueriesService.findByMaxPrice(price);
    }


    @ApiOperation(value = "Retorna uma lista de receitas pelo ingrediente desejado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de receitas pelo seu ingrediente retornados com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findRecipeByIngredient")
    public List<RecipeIngredientDTO> findByIngredient(@RequestParam String ingredient) {
        return recipeQueriesService.findByIngredient(ingredient);
    }


    @ApiOperation(value = "Retorna uma lista de receitas pelo tempo de preparo.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de receitas pelo seu tempo de preparo retornada com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findRecipeByPrepareTime")
    public List<RecipeFormed> findByPrepareTime(@RequestParam Integer prepareTime) {
        return recipeQueriesService.findByPrepareTime(prepareTime);
    }


    @ApiOperation(value = "Retorna uma lista de receitas pelo maximo de calorias.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de receitas pelo seu maximo de calorias retornada com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findByMaxCalories")
    public List<RecipeFormed> findByMaxCalories(@RequestParam BigDecimal calories) {
        return recipeQueriesService.findByMaxCalories(calories);
    }


    @ApiOperation(value = "Retorna uma lista de receitas ordenada pelo tempo de preparo.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de receitas ordenada por tempo de preparo retornada com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/orderByPrepareTime")
    public Page<RecipeFormed> orderForTimePrepare(Integer paginaSolicitada, Integer tamanhoDaPagina) {
        return recipeQueriesService.orderForTimePrepare(paginaSolicitada, tamanhoDaPagina);
    }


    @ApiOperation(value = "Retorna uma lista de receitas ordenada pelo preço.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de receitas ordenada por preço retornada com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/orderForPrice")
    public Page<RecipeFormed> orderForPrice(Integer paginaSolicitada, Integer tamanhoDaPagina) {
        return recipeQueriesService.orderForPrice(paginaSolicitada, tamanhoDaPagina);
    }


    @ApiOperation(value = "Retorna uma receita com seus comentários.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "receita e seus comentários retornados com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findComentsOfRecipe")
    public List<RecipeComentDTO> findComentsOfRecipe(@RequestParam(required = false) Long idRecipe) {
        return recipeQueriesService.findComentsOfRecipe(idRecipe);
    }


    @ApiOperation(value = "Retorna uma receita e suas notas.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "receita e suas notas retornadas com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/findRatingOfRecipe")
    public List<RecipeRatingDTO> findRatingsOfRecipe(@RequestParam(required = false) Long idRecipe) {
        return recipeQueriesService.findRatingsOfRecipe(idRecipe);
    }

}
