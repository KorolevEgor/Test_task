package com.korolyovegor.petList.service.impl;

import com.korolyovegor.petList.model.security.*;
import com.korolyovegor.petList.repository.security.RoleRepository;
import com.korolyovegor.petList.repository.security.UserRepository;
import com.korolyovegor.petList.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder encoder
                           ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User register(User user) {
        Role role = roleRepository.findByName(RoleNameType.ROLE_USER);
        List<Role> userRoles = List.of(role);

        user.setRoles(userRoles);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(UserStatusType.ACTIVE);
        user.setPets(new ArrayList<>());

        System.out.println(user);
        User savedUser = userRepository.save(user);
        System.out.println(savedUser);
        return savedUser;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public void delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public boolean isUsernameFree(String username) {
        return findByUsername(username).isEmpty();
    }

    public PasswordEncoder getEncoder() {
        return encoder;
    }
}
