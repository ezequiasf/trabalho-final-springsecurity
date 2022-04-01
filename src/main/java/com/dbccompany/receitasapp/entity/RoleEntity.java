package com.dbccompany.receitasapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "ROLE")
public class RoleEntity implements Serializable, GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ROLE")
    @SequenceGenerator(name = "GEN_ROLE", sequenceName = "SEQ_ROLE", allocationSize = 1)
    @Column(name = "ROLE_ID")
    private Long roleId;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @JsonIgnore
    @OneToOne(mappedBy = "roleEntity")
    private UserEntity userEntity;
    @Override
    public String getAuthority() {
        return this.getRoleName();
    }
}
