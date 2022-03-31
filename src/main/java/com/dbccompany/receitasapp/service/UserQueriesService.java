package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.*;
import com.dbccompany.receitasapp.dataTransferQuery.UserComentDTO;
import com.dbccompany.receitasapp.dataTransferQuery.UserRatingDTO;
import com.dbccompany.receitasapp.dataTransferQuery.UserRecipeDTO;
import com.dbccompany.receitasapp.entity.UserEntity;
import com.dbccompany.receitasapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserQueriesService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public List<UserRecipeDTO> findRecipesOfUser(Long userId) {
        if (userId != null) {
            return userRepository.findById(userId).stream()
                    .map(user -> (UserRecipeDTO) buildDataTransfer(user, new UserRecipeDTO()))
                    .collect(Collectors.toList());
        }
        return userRepository.findAll().stream()
                .map(user -> (UserRecipeDTO) buildDataTransfer(user, new UserRecipeDTO()))
                .collect(Collectors.toList());
    }

    public List<UserRatingDTO> findRatingsOfUser(Long userId) {
        if (userId != null) {
            return userRepository.findById(userId).stream()
                    .map(user -> (UserRatingDTO) buildDataTransfer(user, new UserRatingDTO()))
                    .collect(Collectors.toList());
        }
        return userRepository.findAll().stream()
                .map(user -> (UserRatingDTO) buildDataTransfer(user, new UserRatingDTO()))
                .collect(Collectors.toList());
    }

    public List<UserComentDTO> findComentsOfUser(Long userId) {
        if (userId != null) {
            return userRepository.findById(userId).stream()
                    .map(user -> (UserComentDTO) buildDataTransfer(user, new UserComentDTO()))
                    .collect(Collectors.toList());
        }
        return userRepository.findAll().stream()
                .map(user -> (UserComentDTO) buildDataTransfer(user, new UserComentDTO()))
                .collect(Collectors.toList());
    }

    private UserUpdate buildDataTransfer(UserEntity user, UserUpdate dataTransfer) {
        if (dataTransfer instanceof UserComentDTO) {
            UserComentDTO userWithComent = objectMapper.convertValue(user, UserComentDTO.class);
            userWithComent.setComents(findComents(user));
            return userWithComent;
        } else if (dataTransfer instanceof UserRatingDTO) {
            UserRatingDTO userWithRating = objectMapper.convertValue(user, UserRatingDTO.class);
            userWithRating.setRatings(findRatings(user));
            return userWithRating;
        }
        UserRecipeDTO userWithRecipes = objectMapper.convertValue(user, UserRecipeDTO.class);
        userWithRecipes.setRecipes(findRecipes(user));
        return userWithRecipes;
    }

    private List<ComentFormed> findComents(UserEntity userEntity) {
        return userEntity.getComents().stream()
                .map(coment -> objectMapper.convertValue(coment, ComentFormed.class))
                .collect(Collectors.toList());
    }

    private List<RatingFormed> findRatings(UserEntity userEntity) {
        return userEntity.getRatings().stream()
                .map(rating -> objectMapper.convertValue(rating, RatingFormed.class))
                .collect(Collectors.toList());
    }

    private List<RecipeFormed> findRecipes(UserEntity userEntity) {
        return userEntity.getRecipes().stream()
                .map(recipe -> objectMapper.convertValue(recipe, RecipeFormed.class))
                .collect(Collectors.toList());
    }

    public List<UserFormed> findByNameContainingIgnoreCase(String userName) {
        return userRepository.findByUserNameContainingIgnoreCase(userName)
                .stream()
                .map(userEntity -> objectMapper.convertValue(userEntity, UserFormed.class))
                .collect(Collectors.toList());
    }

    public List<UserFormed> listAllUsersOrderByName() {
        return userRepository.listAllUsersOrderByName().stream()
                .map(userEntity -> objectMapper.convertValue(userEntity, UserFormed.class))
                .collect(Collectors.toList());
    }

}
