package com.dbccompany.receitasapp.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleType {
    STANDARD(1), PREMIUM(2);
    private final Integer type;
}
