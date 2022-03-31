package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.dataTransfer.UserCreate;
import com.dbccompany.receitasapp.dataTransfer.UserFormed;
import com.dbccompany.receitasapp.dataTransfer.UserUpdate;
import com.dbccompany.receitasapp.entity.UserEntity;
import com.dbccompany.receitasapp.exceptions.ObjectNotFoundException;
import com.dbccompany.receitasapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public List<UserFormed> readAllUsers() {
        return convertList(userRepository.findAll());
    }

    public UserFormed findUserById(Long idUser) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Encontrar por id.");
        UserEntity u = userRepository.findById(idUser).orElseThrow(() ->
                new ObjectNotFoundException("User not found!"));
        log.info("Feita verificação do ID.");
        return objectMapper.convertValue(u, UserFormed.class);
    }

    public UserFormed saveUser(UserCreate userCreate) {
        log.info("Chamada de método service:: Salvar usuários.");
        UserEntity u = objectMapper.convertValue(userCreate, UserEntity.class);
        log.info("Objeto DTO convertido para tipo Usuario.");
        u.setIsActive(true);
        UserEntity u2 = userRepository.save(u);
        log.info("Usuário salvo no repositório.");
        return objectMapper.convertValue(u2, UserFormed.class);
    }

    public UserFormed updateUser(UserUpdate userUpdate, Long idUser) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Atualizar usuários.");
        UserEntity oldUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ObjectNotFoundException("User not found!"));
        UserEntity newUser = objectMapper.convertValue(userUpdate, UserEntity.class);
        log.info("Objeto DTO convertido para tipo Usuario.");
        oldUser.setUserName(newUser.getUserName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setIsActive(newUser.getIsActive());
        userRepository.save(oldUser);
        return objectMapper.convertValue(oldUser, UserFormed.class);
    }

    public void deleteUser(Long idUser) throws ObjectNotFoundException {
        log.info("Chamada de método service:: Deletar usuários.");
        UserEntity userEntity = userRepository.findById(idUser)
                .orElseThrow(() -> new ObjectNotFoundException("User not found!"));
        userEntity.setIsActive(false);
        userRepository.save(userEntity);
        log.info("Usúario desativado.");
    }

    private List<UserFormed> convertList(List<UserEntity> users) {
        log.info("Iniciando conversão de lista...");
        return users
                .stream()
                .map(u -> objectMapper.convertValue(u, UserFormed.class))
                .collect(Collectors.toList());
    }
}
