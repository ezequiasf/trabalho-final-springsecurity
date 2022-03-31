package com.dbccompany.receitasapp.controller;

import com.dbccompany.receitasapp.dataTransfer.RatingCreate;
import com.dbccompany.receitasapp.dataTransfer.RatingFormed;
import com.dbccompany.receitasapp.dataTransfer.RatingUpdate;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.service.RatingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @ApiOperation(value = "Retorna uma lista de notas registradas.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "As notas foram listadas com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema."),})
    @GetMapping("/readAllRatings")
    public List<RatingFormed> readAllRatings() {
        return ratingService.readAllRatings();
    }

    @ApiOperation(value = "Encontra uma nota registrada através do id informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "A nota foi informada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/{idRating}")
    public RatingFormed findRatingById(@PathVariable("idRating") Long idRating) throws ObjectNotFoundException {
        return ratingService.findRatingById(idRating);
    }

    @ApiOperation(value = "Cadastra uma nota no banco de dados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cadastrou a nota com sucesso no banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @PostMapping("/saveRating/{idUser}")
    @Validated
    public RatingFormed saveRating(@Valid @RequestBody RatingCreate ratingCreate, @PathVariable("idUser") Long idUser) throws ObjectNotFoundException {
        return ratingService.saveRating(ratingCreate, idUser);
    }

    @ApiOperation(value = "Atualiza uma nota no banco de dados através do id informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Atualizou com sucesso a nota no banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @PutMapping("/updateRating/{idRating}")
    @Validated
    public RatingFormed updateRating(@Valid @RequestBody RatingUpdate ratingUpdate, @PathVariable("idRating") Long idRating) throws ObjectNotFoundException {
        return ratingService.updateRating(ratingUpdate, idRating);
    }

    @ApiOperation(value = "Deleta uma nota do banco de dados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deletou a nota com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @DeleteMapping("/deleteRating/{idRating}")
    public void deleteRating(@PathVariable("idRating") Long idRating) throws ObjectNotFoundException {
        ratingService.deleteRating(idRating);
    }

}
