package com.dbccompany.receitasapp.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleType {
    STANDARD("ROLE_STANDARD"), PREMIUM("ROLE_PREMIUM");
    private final String type;
}
