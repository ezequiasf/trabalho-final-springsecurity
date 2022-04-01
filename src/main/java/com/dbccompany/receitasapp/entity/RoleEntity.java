package com.dbccompany.receitasapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "ROLE")
public class RoleEntity implements Serializable, GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ROLE")
    @SequenceGenerator(name = "GEN_ROLE", sequenceName = "seq_role", allocationSize = 1)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;
    @JsonIgnore
    @OneToMany(mappedBy = "roleEntity")
    private Set<UserEntity> userEntities;
    @Override
    public String getAuthority() {
        return this.getRoleName();
    }
}
