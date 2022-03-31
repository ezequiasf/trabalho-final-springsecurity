package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.*;
import com.dbccompany.receitasapp.dataTransferQuery.RecipeComentDTO;
import com.dbccompany.receitasapp.dataTransferQuery.RecipeIngredientDTO;
import com.dbccompany.receitasapp.dataTransferQuery.RecipeRatingDTO;
import com.dbccompany.receitasapp.entity.RecipeEntity;
import com.dbccompany.receitasapp.repository.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeQueriesService {
    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;


    public List<RecipeFormed> findByMaxPrice(BigDecimal price) {
        return recipeRepository.findByMaxPrice(price).stream()
                .map(recipeEntity -> objectMapper.convertValue(recipeEntity, RecipeFormed.class))
                .collect(Collectors.toList());
    }

    public List<RecipeIngredientDTO> findByIngredient(String ingredient) {
        return recipeRepository.findAll()
                .stream()
                .filter(recipeEntity -> recipeEntity.getIngredientEntities().stream()
                        .anyMatch(ingredientEntity -> ingredientEntity.getIngredient().toUpperCase().contains(ingredient.toUpperCase())))
                .map(recipeEntity -> (RecipeIngredientDTO) buildDataTransfer(recipeEntity, new RecipeIngredientDTO()))
                .collect(Collectors.toList());
    }

    public List<RecipeFormed> findByPrepareTime(Integer prepareTime) {
        return recipeRepository.findByPrepareTime(prepareTime).stream()
                .map(recipeEntity -> objectMapper.convertValue(recipeEntity, RecipeFormed.class))
                .collect(Collectors.toList());
    }


    public List<RecipeFormed> findByMaxCalories(BigDecimal calories) {
        return recipeRepository.findByMaxCalories(calories).stream()
                .map(recipeEntity -> objectMapper.convertValue(recipeEntity, RecipeFormed.class))
                .collect(Collectors.toList());
    }

    public Page<RecipeFormed> orderForPrice(Integer paginaSolicitada, Integer tamanhoDaPagina) {
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoDaPagina, Sort.by("price").ascending());
        return recipeRepository.findAll(pageable)
                .map(recipeEntity -> objectMapper.convertValue(recipeEntity, RecipeFormed.class));
    }


    public Page<RecipeFormed> orderForTimePrepare(Integer paginaSolicitada, Integer tamanhoDaPagina) {
        Pageable pageable = PageRequest.of(paginaSolicitada, tamanhoDaPagina, Sort.by("prepareTime").ascending());
        return recipeRepository.findAll(pageable)
                .map(recipeEntity -> objectMapper.convertValue(recipeEntity, RecipeFormed.class));
    }


    public List<RecipeComentDTO> findComentsOfRecipe(Long idRecipe) {
        if (idRecipe != null) {
            return recipeRepository.findById(idRecipe).stream()
                    .map(recipe -> (RecipeComentDTO) buildDataTransfer(recipe, new RecipeComentDTO()))
                    .collect(Collectors.toList());
        }
        return recipeRepository.findAll().stream()
                .map(recipe -> (RecipeComentDTO) buildDataTransfer(recipe, new RecipeComentDTO()))
                .collect(Collectors.toList());
    }

    public List<RecipeRatingDTO> findRatingsOfRecipe(Long idRecipe) {
        if (idRecipe != null) {
            return recipeRepository.findById(idRecipe).stream()
                    .map(user -> (RecipeRatingDTO) buildDataTransfer(user, new RecipeRatingDTO()))
                    .collect(Collectors.toList());
        }
        return recipeRepository.findAll().stream()
                .map(recipe -> (RecipeRatingDTO) buildDataTransfer(recipe, new RecipeRatingDTO()))
                .collect(Collectors.toList());
    }

    private RecipeCreate buildDataTransfer(RecipeEntity recipe, RecipeCreate dataTransfer) {
        if (dataTransfer instanceof RecipeComentDTO) {
            RecipeComentDTO recipewithcoment = objectMapper.convertValue(recipe, RecipeComentDTO.class);
            recipewithcoment.setComents(findComents(recipe));
            return recipewithcoment;
        } else if (dataTransfer instanceof RecipeIngredientDTO) {
            RecipeIngredientDTO recipeWithIngredient = objectMapper.convertValue(recipe, RecipeIngredientDTO.class);
            recipeWithIngredient.setIngredientFormeds(findIngredients(recipe));
            return recipeWithIngredient;
        }
        RecipeRatingDTO recipeRatingDTO = objectMapper.convertValue(recipe, RecipeRatingDTO.class);
        recipeRatingDTO.setRatings(findRatings(recipe));
        return recipeRatingDTO;
    }


    private List<ComentFormed> findComents(RecipeEntity recipeEntity) {
        return recipeEntity.getComentEntities().stream()
                .map(coment -> objectMapper.convertValue(coment, ComentFormed.class))
                .collect(Collectors.toList());
    }

    private List<RatingFormed> findRatings(RecipeEntity recipeEntity) {
        return recipeEntity.getRatingEntities().stream()
                .map(rating -> objectMapper.convertValue(rating, RatingFormed.class))
                .collect(Collectors.toList());
    }

    private List<IngredientFormed> findIngredients(RecipeEntity recipeEntity) {
        return recipeEntity.getIngredientEntities().stream()
                .map(ing -> objectMapper.convertValue(ing, IngredientFormed.class))
                .collect(Collectors.toList());
    }
}
