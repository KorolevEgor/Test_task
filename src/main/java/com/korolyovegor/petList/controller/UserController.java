package com.korolyovegor.petList.controller;

import com.korolyovegor.petList.model.security.User;
import com.korolyovegor.petList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class UserController {

    private final UserService userService;

    @Resource(name="authenticationManager")
    private AuthenticationManager authManager;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logOut")
    public void logOut() {
        SecurityContextHolder.clearContext();
    }

    @PostMapping(value = "/login")
    public void login(HttpServletRequest request,
                      @RequestParam("username") final String username,
                      @RequestParam("password") final String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println(e.getMessage());
        }
    }

//    @PreAuthorize("permitAll()")
    @PostMapping("/registration")
    public void registration(HttpServletRequest request, @RequestBody User user) {
        userService.register(user);
        login(request, user.getUsername(), user.getPassword());
    }

    @GetMapping("/existsUser/{username}")
    public boolean findUserById(@PathVariable String username) {
        return userService.findByUsername(username).isPresent();
    }
}
