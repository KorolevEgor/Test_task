package com.korolyovegor.petList.model.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.korolyovegor.petList.model.Pet;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "users_username_key", columnList = "username", unique = true),
        @Index(name = "users_email_key", columnList = "email", unique = true)
})
@Data
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "username", nullable = false, length = 30)
    private String username;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private UserStatusType status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Pet> pets;

    public User(String username, String email, String firstName, String lastName, String password, UserStatusType status) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.status = status;
//        this.pets = new ArrayList<>();
    }
}