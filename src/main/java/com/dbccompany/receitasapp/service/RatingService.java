package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.*;
import com.dbccompany.receitasapp.entity.RatingEntity;
import com.dbccompany.receitasapp.entity.RecipeEntity;
import com.dbccompany.receitasapp.entity.UserEntity;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.repository.RatingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final RecipeService recipeService;

    public List<RatingFormed> readAllRatings() {
        log.info("Chamada de método service:: Ler todas as notas.");
        return converterLista(ratingRepository.findAll());
    }

    public RatingFormed findRatingById(Long idRating) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Encontrar por id.");
        RatingEntity r = ratingRepository.findById(idRating).orElseThrow(() -> new ObjectNotFoundException("Rating not found"));
        return objectMapper.convertValue(r, RatingFormed.class);
    }

    public RatingFormed saveRating(RatingCreate ratingCreate, Long idUser) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Salvar notas.");
        UserFormed userFormed = userService.findUserById(idUser);
        RecipeFormed recipeFormed = recipeService.findRecipeById(ratingCreate.getIdRecipe());
        RatingEntity r = objectMapper.convertValue(ratingCreate, RatingEntity.class);
        log.info("Objeto DTO convertido para tipo Nota.");
        r.setUserEntity(objectMapper.convertValue(userFormed, UserEntity.class));
        r.setRecipeEntity(objectMapper.convertValue(recipeFormed, RecipeEntity.class));
        RatingEntity r2 = ratingRepository.save(r);
        log.info("Nota salva no repositório.");
        return objectMapper.convertValue(r2, RatingFormed.class);
    }

    public RatingFormed updateRating(RatingUpdate ratingUpdate, Long idRating) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Atualizar notas.");
        RatingEntity oldRating = ratingRepository.findById(idRating)
                .orElseThrow(() -> new ObjectNotFoundException("Rating not found"));
        RatingEntity newRating = objectMapper.convertValue(ratingUpdate, RatingEntity.class);
        log.info("Objeto DTO convertido para tipo Nota.");
        oldRating.setRating(newRating.getRating());
        log.info("Nota atualizada no repositório.");
        RatingEntity ratingReturn = ratingRepository.save(oldRating);
        return objectMapper.convertValue(ratingReturn, RatingFormed.class);
    }

    public void deleteRating(Long idRating) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Deletar notas.");
        RatingEntity ratingEntity = ratingRepository.findById(idRating)
                .orElseThrow(() -> new ObjectNotFoundException("Rating not registered!"));
        ratingRepository.delete(ratingEntity);
        log.info("Nota deletada no repositório.");
    }

    private List<RatingFormed> converterLista(List<RatingEntity> ratingEntities) {
        log.info("Iniciando conversão de lista...");
        return ratingEntities
                .stream()
                .map(rating -> objectMapper.convertValue(rating, RatingFormed.class))
                .collect(Collectors.toList());
    }
}
