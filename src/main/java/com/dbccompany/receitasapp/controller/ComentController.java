package com.dbccompany.receitasapp.controller;

import com.dbccompany.receitasapp.dataTransfer.ComentCreate;
import com.dbccompany.receitasapp.dataTransfer.ComentFormed;
import com.dbccompany.receitasapp.dataTransfer.ComentUpdate;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.service.ComentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/coment")
@RequiredArgsConstructor
public class ComentController {
    private final ComentService comentService;

    @ApiOperation(value = "Retorna uma lista de comentários registrados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Os comentários foram listados com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema."),})
    @GetMapping("/readAllComents")
    public List<ComentFormed> readAllComents() {
        return comentService.readAllComents();
    }

    @ApiOperation(value = "Encontra um comentário registrado através do id informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "O comentário foi informado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @GetMapping("/{idComent}")
    public ComentFormed findComentById(@PathVariable("idComent") Long idComent) throws ObjectNotFoundException {
        return comentService.findById(idComent);
    }

    @ApiOperation(value = "Cadastra um comentário no banco de dados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cadastrou o comentário com sucesso no banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @PostMapping("/saveComent/{idUser}")
    @Validated
    public ComentFormed saveComent(@Valid @RequestBody ComentCreate comentCreate, @PathVariable("idUser") Long idUser) throws ObjectNotFoundException {
        return comentService.saveComent(comentCreate, idUser);
    }

    @ApiOperation(value = "Atualiza um comentário no banco de dados através do id informado.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Atualizou com sucesso o comentário no banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @PutMapping("/updateComent/{idComent}")
    @Validated
    public ComentFormed updateComent(@Valid @RequestBody ComentUpdate comentUpdate, @PathVariable("idComent") Long idComent) throws ObjectNotFoundException {
        return comentService.updateComent(comentUpdate, idComent);
    }

    @ApiOperation(value = "Deleta um comentário do banco de dados.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deletou o comentário com sucesso do banco."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    @DeleteMapping("/deleteComent/{idComent}")
    public void deleteComentById(@PathVariable("idComent") Long idComent) throws ObjectNotFoundException {
        comentService.deleteComent(idComent);
    }

}
