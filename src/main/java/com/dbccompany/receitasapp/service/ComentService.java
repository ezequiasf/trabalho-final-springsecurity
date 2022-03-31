package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.*;
import com.dbccompany.receitasapp.entity.ComentEntity;
import com.dbccompany.receitasapp.entity.RecipeEntity;
import com.dbccompany.receitasapp.entity.UserEntity;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.repository.ComentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComentService {
    private final ComentRepository comentRepository;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final RecipeService recipeService;

    public List<ComentFormed> readAllComents() {
        log.info("Chamada de método service:: Ler todas as notas.");
        return convertList(comentRepository.findAll());
    }

    public ComentFormed findById(Long idComent) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Encontrar por id.");
        ComentEntity c = comentRepository.findById(idComent).orElseThrow(() ->
                new ObjectNotFoundException("Coment not found!"));
        log.info("Feita verificação do ID.");
        return objectMapper.convertValue(c, ComentFormed.class);
    }

    public ComentFormed saveComent(ComentCreate comentCreate, Long idUser) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Salvar comentarios.");
        UserFormed user = userService.findUserById(idUser);
        RecipeFormed recipe = recipeService.findRecipeById(comentCreate.getIdRecipe());
        ComentEntity c = objectMapper.convertValue(comentCreate, ComentEntity.class);
        log.info("Objeto DTO convertido para tipo Comentario.");
        c.setUserEntity(objectMapper.convertValue(user, UserEntity.class));
        c.setRecipe(objectMapper.convertValue(recipe, RecipeEntity.class));
        ComentEntity c2 = comentRepository.save(c);
        log.info("Comentario salvo no repositório.");
        return objectMapper.convertValue(c2, ComentFormed.class);
    }

    public ComentFormed updateComent(ComentUpdate comentUpdate, Long idComent) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Atualizar comentários.");
        ComentEntity oldComent = comentRepository.findById(idComent)
                .orElseThrow(() -> new ObjectNotFoundException("Coment not found!"));
        ComentEntity comentReceived = objectMapper.convertValue(comentUpdate, ComentEntity.class);

        log.info("Objeto DTO convertido para tipo Comentario.");
        oldComent.setComent(comentReceived.getComent());
        ComentEntity newComent = comentRepository.save(oldComent);

        log.info("Comentário atualizado no repositório.");
        return objectMapper.convertValue(newComent, ComentFormed.class);
    }

    public void deleteComent(Long idComent) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Deletar comentários.");
        ComentEntity coment = comentRepository.findById(idComent)
                .orElseThrow(() -> new ObjectNotFoundException("Coment not registered!"));
        comentRepository.delete(coment);
    }

    private List<ComentFormed> convertList(List<ComentEntity> coments) {
        log.info("Iniciando conversão de lista...");
        return coments
                .stream()
                .map(coment -> objectMapper.convertValue(coment, ComentFormed.class))
                .collect(Collectors.toList());
    }
}
