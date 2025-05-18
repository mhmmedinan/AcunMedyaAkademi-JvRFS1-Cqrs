package com.rentacarcqrs.domain.entities;

import io.github.mhmmedinan.core_persistence.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction(value = "deleted_date IS NULL")
public class Role extends BaseEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private Set<UserRole> userRoles;

    @Override
    public String getAuthority() {
        return this.name.toLowerCase(); //ADMIN
    }
}
