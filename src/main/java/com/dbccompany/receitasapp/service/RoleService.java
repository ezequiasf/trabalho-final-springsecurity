package com.dbccompany.receitasapp.service;

import com.dbccompany.receitasapp.entity.RoleEntity;
import com.dbccompany.receitasapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleEntity findRoleByName (String name){
        Optional<RoleEntity> role = roleRepository.findByRoleName(name);
        return role.orElse(null);
    }
}
