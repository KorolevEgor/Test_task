package com.korolyovegor.petList.repository.security;

import com.korolyovegor.petList.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    default User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user authenticated"));
        return user;
    }
}
