package com.dbccompany.receitasapp.security;

import com.dbccompany.receitasapp.entity.UserEntity;
import com.dbccompany.receitasapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<UserEntity> optUser = userService.findUserByName(name);
        if (optUser.isPresent()){
            return optUser.get();
        }
        throw new UsernameNotFoundException("User not found!");
    }
}
