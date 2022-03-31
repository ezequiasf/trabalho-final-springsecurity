package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.RecipeCreate;
import com.dbccompany.receitasapp.dataTransfer.RecipeFormed;
import com.dbccompany.receitasapp.dataTransfer.UserFormed;
import com.dbccompany.receitasapp.entity.RecipeEntity;
import com.dbccompany.receitasapp.entity.UserEntity;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.exceptions.UserNotActiveException;
import com.dbccompany.receitasapp.repository.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    public List<RecipeFormed> readAllRecipes() {
        return convertList(recipeRepository.findAll());
    }

    public RecipeFormed findRecipeById(Long idRecipe) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Encontrar por id.");
        RecipeEntity r = recipeRepository.findById(idRecipe).orElseThrow(() -> new ObjectNotFoundException("Recipe not found!"));
        return objectMapper.convertValue(r, RecipeFormed.class);
    }

    public RecipeFormed saveRecipe(RecipeCreate recipeCreate, Long idUser) throws ObjectNotFoundException, UserNotActiveException {
        log.info("Chamada de método service:: Salvar receitas.");
        UserFormed userFormed = userService.findUserById(idUser);
        UserEntity userEntity = objectMapper.convertValue(userFormed, UserEntity.class);
        if (!userEntity.getIsActive()) {
            throw new UserNotActiveException("User inactive!");
        }
        RecipeEntity r = objectMapper.convertValue(recipeCreate, RecipeEntity.class);
        log.info("Objeto DTO convertido para tipo Receita.");
        r.setUserEntity(userEntity);
        RecipeEntity r2 = recipeRepository.save(r);
        log.info("Receita salva no repositório.");
        return objectMapper.convertValue(r2, RecipeFormed.class);
    }

    public RecipeFormed updateRecipe(RecipeCreate recipeCreate, Long idRecipe) throws ObjectNotFoundException, UserNotActiveException {
        log.info("Chamada de método service:: Atualizar receitas.");
        RecipeEntity oldRecipe = recipeRepository.findById(idRecipe)
                .orElseThrow(() -> new ObjectNotFoundException("Recipe not found!"));
        if (!oldRecipe.getUserEntity().getIsActive()) {
            throw new UserNotActiveException("User inactive!");
        }
        RecipeEntity newRecipe = objectMapper.convertValue(recipeCreate, RecipeEntity.class);
        log.info("Objeto DTO convertido para tipo Receita.");
        oldRecipe.setRecipeName(newRecipe.getRecipeName());
        oldRecipe.setPrepareRecipe(newRecipe.getPrepareRecipe());
        oldRecipe.setCalories(newRecipe.getCalories());
        oldRecipe.setImageUrl(newRecipe.getImageUrl());
        oldRecipe.setPrice(newRecipe.getPrice());
        oldRecipe.setPrepareTime(newRecipe.getPrepareTime());
        log.info("Receita atualizada no repositório.");
        RecipeEntity recipeReturn = recipeRepository.save(oldRecipe);
        return objectMapper.convertValue(recipeReturn, RecipeFormed.class);
    }

    public void deleteRecipe(Long idRecipe) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Deletar receitas.");
        RecipeEntity recipeEntity = recipeRepository.findById(idRecipe).orElseThrow(() ->
                new ObjectNotFoundException("Recipe not found!"));
        recipeRepository.delete(recipeEntity);
    }

    private List<RecipeFormed> convertList(List<RecipeEntity> recipes) {
        log.info("Iniciando conversão de lista...");
        return recipes
                .stream()
                .map(r -> objectMapper.convertValue(r, RecipeFormed.class))
                .collect(Collectors.toList());
    }
}
